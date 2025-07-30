package com.example.driedfruitstore.controller.user;

import com.example.driedfruitstore.model.dto.request.UserEditParamsRequest;
import com.example.driedfruitstore.model.dto.request.UserEditPasswordRequest;
import com.example.driedfruitstore.model.dto.user.UserDTO;
import com.example.driedfruitstore.service.facade.user.UserEditFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/edit")
@RequiredArgsConstructor
public class UserEditController {
    private final UserEditFacade userEditFacade;

    @PostMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO editPassword(@RequestBody UserEditPasswordRequest userEditPasswordRequestDTO) {
        return userEditFacade.editPassword(userEditPasswordRequestDTO);
    }

    @PostMapping("/params")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO editParams(@RequestBody UserEditParamsRequest userEditParamsRequestDTO) {
        return userEditFacade.editUserParams(userEditParamsRequestDTO);
    }



}
