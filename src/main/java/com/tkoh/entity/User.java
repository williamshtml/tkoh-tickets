package com.tkoh.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table (name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
        
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true) // es para que no se repita
private String username;

@Column(nullable = false)  // es para no dejar espacios en blanco
private String password;

@Column(nullable = false)
private String role;

}


