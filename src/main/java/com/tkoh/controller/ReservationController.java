package com.tkoh.controller;

import com.tkoh.dto.ReservationRequestDTO;
import com.tkoh.dto.ReservationResponseDTO;
import com.tkoh.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReservationResponseDTO> reserve(@Valid @RequestBody ReservationRequestDTO request) {
        return ResponseEntity.ok(reservationService.createReservation(request));
    }

    //  NUEVO MÉTODO: Cancelar reserva y regenerar stock
    // Lo permitimos para ADMIN o USER (el usuario puede cancelar su propia reserva)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}