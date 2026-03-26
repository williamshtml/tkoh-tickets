package com.tkoh.mapper;

import com.tkoh.dto.UserResponseDTO;
import com.tkoh.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toDTO(User user) {
        if (user == null) return null;
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}