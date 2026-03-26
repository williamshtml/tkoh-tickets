package com.tkoh.service;

import com.tkoh.dto.EventRequestDTO;
import com.tkoh.dto.EventResponseDTO;
import com.tkoh.entity.Event;
import com.tkoh.exception.CustomException;
import com.tkoh.mapper.EventMapper;
import com.tkoh.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Transactional(readOnly = true)
    public List<EventResponseDTO> findAll() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public EventResponseDTO create(EventRequestDTO request) {
        Event event = Event.builder()
                .name(request.getName())
                .description(request.getDescription())
                .totalTickets(request.getTotalTickets())
                .availableTickets(request.getTotalTickets()) // Al inicio, todos disponibles
                .status("ACTIVE")
                .build();

        return eventMapper.toDTO(eventRepository.save(event));
    }
}