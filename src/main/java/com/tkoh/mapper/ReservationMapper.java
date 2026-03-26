package com.tkoh.mapper;

import com.tkoh.dto.ReservationResponseDTO;
import com.tkoh.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public ReservationResponseDTO toDTO(Reservation reservation) {
        if (reservation == null) return null;
        
        return ReservationResponseDTO.builder()
                .id(reservation.getId())
                .eventName(reservation.getEvent().getName())
                .username(reservation.getUser().getUsername()) // 
                .quantity(reservation.getQuantity())           // 
                .reservationDate(reservation.getReservationDate())
                .status(reservation.getStatus())
                .build();
    }
}