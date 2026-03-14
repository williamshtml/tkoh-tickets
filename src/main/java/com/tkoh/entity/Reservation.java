package com.tkoh.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table (name="reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
        
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)//Evita traer todos los datos de un usuario y asi no sobrecargar el sistema xd
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id" , nullable = false)
    private Event event;
    
    private String status;
}
