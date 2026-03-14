package com.tkoh.service;
import com.tkoh.dto.RegisterRequestDTO;
import com.tkoh.dto.AuthResponseDTO;
import com.tkoh.entity.User;
import com.tkoh.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
 private final UserRepository userRepository;

    @Transactional
    public AuthResponseDTO registrarUsuario(RegisterRequestDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        User nuevoUsuario = User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword()) 
                .role("ROLE_USER")
                .build();

        User guardado = userRepository.save(nuevoUsuario);

        return mapToResponseDTO(guardado);
    }

    public List<AuthResponseDTO> listarUsuarios() {
        return userRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public AuthResponseDTO obtenerUsuario(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapToResponseDTO(user);
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }

    // El Mapper ahora usa el nombre de TU archivo
    private AuthResponseDTO mapToResponseDTO(User user) {
        return AuthResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
