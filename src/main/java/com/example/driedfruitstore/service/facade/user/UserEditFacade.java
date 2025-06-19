package com.example.driedfruitstore.service.facade.user;

import com.example.driedfruitstore.model.dto.request.UserEditParamsRequest;
import com.example.driedfruitstore.model.dto.request.UserEditPasswordRequest;
import com.example.driedfruitstore.model.dto.user.UserDTO;

public interface UserEditFacade {
    UserDTO editPassword(UserEditPasswordRequest dto);
    UserDTO editUserParams(UserEditParamsRequest dto);
}
