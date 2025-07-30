package com.example.driedfruitstore.service.inte.user;

import com.example.driedfruitstore.model.dto.request.UserRegisterRequest;
import com.example.driedfruitstore.model.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserAuthenticationService {

    User createUser(UserRegisterRequest userRegisterRequest);
    User createUser(Map<String, Object> attributes);
    Optional<User> findByEmail(String email);

}
