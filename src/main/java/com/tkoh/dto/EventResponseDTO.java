package com.tkoh.dto;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public class EventResponseDTO {
    
    private Long id;
    private String name;
    private Integer availableTickets;  //Tickets Disponibles
    private Integer totalTickets;
    
}
