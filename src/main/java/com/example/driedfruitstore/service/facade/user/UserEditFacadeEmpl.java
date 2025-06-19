package com.example.driedfruitstore.service.facade.user;

import com.example.driedfruitstore.model.dto.request.UserEditParamsRequest;
import com.example.driedfruitstore.model.dto.request.UserEditPasswordRequest;
import com.example.driedfruitstore.model.dto.user.UserDTO;
import com.example.driedfruitstore.model.entity.User;
import com.example.driedfruitstore.service.user.UserService;
import com.example.driedfruitstore.service.auth.AuthUser;
import lombok.RequiredArgsConstructor;
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
