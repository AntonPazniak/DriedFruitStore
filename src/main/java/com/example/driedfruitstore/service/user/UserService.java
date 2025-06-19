package com.example.driedfruitstore.service.user;

import com.example.driedfruitstore.model.dto.request.UserEditParamsRequest;
import com.example.driedfruitstore.model.dto.user.UserDTO;
import com.example.driedfruitstore.model.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    UserDTO editPassword(User user, String oldPassword, String newPassword);
    UserDTO editUser(User user, UserEditParamsRequest dto);
}
