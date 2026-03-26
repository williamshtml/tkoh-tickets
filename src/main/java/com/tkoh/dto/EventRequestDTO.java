package com.tkoh.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDTO {

    @NotBlank(message = "El nombre del evento es obligatorio")
    private String name;

    private String description;

    @NotNull(message = "El total de tickets es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 ticket disponible")
    private Integer totalTickets;
}