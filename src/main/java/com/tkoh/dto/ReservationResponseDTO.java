package com.tkoh.dto;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ReservationResponseDTO {
    
    private Long id;
    private String eventName;
    private String username;
    private String status;
    
}
