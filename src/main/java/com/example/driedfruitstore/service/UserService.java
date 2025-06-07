package com.example.driedfruitstore.service;

import com.example.driedfruitstore.dto.request.UserEditParamsRequest;
import com.example.driedfruitstore.dto.user.UserDTO;
import com.example.driedfruitstore.dto.user.UserEditDTO;
import com.example.driedfruitstore.model.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    UserDTO editPassword(User user, String oldPassword, String newPassword);
    UserDTO editUser(User user, UserEditParamsRequest dto);
}
