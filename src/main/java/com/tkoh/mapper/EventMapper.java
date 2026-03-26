package com.tkoh.mapper;

import com.tkoh.dto.EventResponseDTO;
import com.tkoh.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventResponseDTO toDTO(Event event) {
        if (event == null) return null;
        return EventResponseDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .availableTickets(event.getAvailableTickets())
                .status(event.getStatus())
                .build();
    }
}