package com.example.driedfruitstore.service;

import com.example.driedfruitstore.model.entity.User;

public interface UserService {

    void save(User user);
    User findById(Long id);
}
