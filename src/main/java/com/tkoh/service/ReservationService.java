package com.tkoh.service;

import com.tkoh.dto.ReservationRequestDTO;
import com.tkoh.dto.ReservationResponseDTO;
import com.tkoh.entity.Event;
import com.tkoh.entity.Reservation;
import com.tkoh.entity.User;
import com.tkoh.exception.CustomException;
import com.tkoh.mapper.ReservationMapper;
import com.tkoh.repository.EventRepository;
import com.tkoh.repository.ReservationRepository;
import com.tkoh.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, EventRepository eventRepository,
                              UserRepository userRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.reservationMapper = reservationMapper;
    }

    @Transactional
    public ReservationResponseDTO createReservation(ReservationRequestDTO request) {
        // 1. Obtener usuario autenticado desde el SecurityContext
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("Usuario no encontrado", HttpStatus.NOT_FOUND));

        // 2. Buscar el evento y validar stock
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new CustomException("Evento no encontrado", HttpStatus.NOT_FOUND));

        if (event.getAvailableTickets() < request.getQuantity()) {
            throw new CustomException("No hay suficientes tickets disponibles", HttpStatus.BAD_REQUEST);
        }

        // 3. Descontar stock
        event.setAvailableTickets(event.getAvailableTickets() - request.getQuantity());
        eventRepository.save(event);

        // 4. Crear la reserva
        Reservation reservation = Reservation.builder()
                .user(user)
                .event(event)
                .quantity(request.getQuantity())
                .reservationDate(LocalDateTime.now())
                .status("CONFIRMED")
                .build();

        return reservationMapper.toDTO(reservationRepository.save(reservation));
    }

    // 🚀 NUEVO MÉTODO: Cancelar y regenerar tickets
    @Transactional
    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new CustomException("Reserva no encontrada", HttpStatus.NOT_FOUND));

        // 🔄 REGENERACIÓN: Devolvemos los tickets al stock del evento
        Event event = reservation.getEvent();
        event.setAvailableTickets(event.getAvailableTickets() + reservation.getQuantity());
        eventRepository.save(event);

        // Borramos la reserva de la base de datos
        reservationRepository.delete(reservation);
    }
}