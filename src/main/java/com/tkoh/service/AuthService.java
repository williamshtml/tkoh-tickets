package com.tkoh.service;

import com.tkoh.dto.AuthResponseDTO;
import com.tkoh.dto.LoginRequestDTO;
import com.tkoh.dto.RegisterRequestDTO;
import com.tkoh.entity.User;
import com.tkoh.exception.CustomException;
import com.tkoh.repository.UserRepository;
import com.tkoh.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, 
                       JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
        // 1. Verificar si el usuario ya existe
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new CustomException("El nombre de usuario ya está en uso", HttpStatus.BAD_REQUEST);
        }

        // 2. Crear y guardar el usuario (siempre ROLE_USER por defecto)
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .build();

        User savedUser = userRepository.save(user);
        
        // 3. Generar token
        String token = jwtUtil.generateToken(savedUser.getUsername(), savedUser.getRole());

        return AuthResponseDTO.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .token(token)
                .build();
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        // 1. Autenticar con Spring Security
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception e) {
            throw new CustomException("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
        }

        // 2. Buscar usuario para obtener su rol y generar token
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException("Usuario no encontrado", HttpStatus.NOT_FOUND));

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return AuthResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .token(token)
                .build();
    }
}