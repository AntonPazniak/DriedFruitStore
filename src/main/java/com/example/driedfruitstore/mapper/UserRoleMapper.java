package com.example.driedfruitstore.mapper;

import com.example.driedfruitstore.model.dto.user.UserRoleDTO;
import com.example.driedfruitstore.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

    public UserRoleDTO toDTO(User user){
        return
                new UserRoleDTO(
                        user.getLogin(),
                        user.getRoles().stream().map(e -> e.getName().name()).toList()
                );
    }
}
