package com.tkoh.controller;
import com.tkoh.dto.AuthResponseDTO;
import com.tkoh.dto.LoginRequestDTO;
import com.tkoh.dto.RegisterRequestDTO;
import com.tkoh.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
 private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registrar(@RequestBody RegisterRequestDTO dto) {
        return ResponseEntity.ok(authService.registrarUsuario(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @GetMapping("/users")
    public List<AuthResponseDTO> listar() {
        return authService.listarUsuarios();
    }

    @GetMapping("/users/{id}")
    public AuthResponseDTO obtener(@PathVariable Long id) {
        return authService.obtenerUsuario(id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        authService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
    

