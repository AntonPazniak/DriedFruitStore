package com.example.driedfruitstore.service.facade;

import com.example.driedfruitstore.dto.request.UserEditParamsRequest;
import com.example.driedfruitstore.dto.request.UserEditPasswordRequest;
import com.example.driedfruitstore.dto.user.UserDTO;
import com.example.driedfruitstore.model.entity.User;
import com.example.driedfruitstore.service.UserService;
import com.example.driedfruitstore.service.auth.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class UserEditFacadeEmpl implements UserEditFacade{

        private final UserService userService;
        private final AuthUser authUser;

        @Override
        public UserDTO editPassword(UserEditPasswordRequest dto) {
            User user = authUser.getAuthenticatedUser();
            return userService.editPassword(user, dto.oldPassword(), dto.newPassword());
        }

        @Override
        public UserDTO editUserParams(UserEditParamsRequest dto) {
            User user = authUser.getAuthenticatedUser();
            return userService.editUser(user, dto);
        }

    }
