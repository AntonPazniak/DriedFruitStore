package com.example.driedfruitstore.mapper;

import com.example.driedfruitstore.dto.user.UserDTO;
import com.example.driedfruitstore.model.entity.User;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return UserDTO.builder()
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .build();
    }
}
