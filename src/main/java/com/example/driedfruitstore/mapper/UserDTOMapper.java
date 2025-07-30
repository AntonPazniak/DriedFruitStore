package com.example.driedfruitstore.mapper;

import com.example.driedfruitstore.model.dto.user.UserDTO;
import com.example.driedfruitstore.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .build();
    }
}
