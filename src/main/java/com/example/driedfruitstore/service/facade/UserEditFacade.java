package com.example.driedfruitstore.service.facade;

import com.example.driedfruitstore.dto.request.UserEditParamsRequest;
import com.example.driedfruitstore.dto.request.UserEditPasswordRequest;
import com.example.driedfruitstore.dto.user.UserDTO;

public interface UserEditFacade {
    UserDTO editPassword(UserEditPasswordRequest dto);
    UserDTO editUserParams(UserEditParamsRequest dto);
}
