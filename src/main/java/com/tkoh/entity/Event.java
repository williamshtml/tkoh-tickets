package com.tkoh.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name="events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Event {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (nullable = false)
    private String name;
    
    @Column (nullable = false)
    private Integer totalTickets;
    
    @Column (nullable = false)
    private Integer availableTickets;
    
    @Version
    private Long version;
    
    @Column (nullable = false)
    private String status;
}
