package com.tkoh.dto;

import java.time.LocalDateTime;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationResponseDTO {
    private Long id;
    private String eventName;
    private String username; //  AÑADIDO PARA EL MAPPER
    private Integer quantity;
    private LocalDateTime reservationDate;
    private String status;
}