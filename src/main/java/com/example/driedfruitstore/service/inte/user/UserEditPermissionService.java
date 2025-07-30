package com.example.driedfruitstore.service.inte.user;

import com.example.driedfruitstore.model.entity.User;

public interface UserEditPermissionService {

    User getUserByLoginOrThrow(String login);
    User save(User user);
    
}
