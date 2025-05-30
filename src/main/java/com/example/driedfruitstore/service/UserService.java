package com.example.driedfruitstore.service;

import com.example.driedfruitstore.model.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
