package com.example.driedfruitstore.controller.user;

import com.example.driedfruitstore.dto.request.UserEditParamsRequest;
import com.example.driedfruitstore.dto.request.UserEditPasswordRequest;
import com.example.driedfruitstore.dto.user.UserDTO;
import com.example.driedfruitstore.service.facade.UserEditFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/edit")
@RequiredArgsConstructor
public class UserEditController {
    private final UserEditFacade userEditFacade;

    @PostMapping("/password")
    public ResponseEntity<UserDTO> editPassword(@RequestBody UserEditPasswordRequest userEditPasswordRequestDTO) {
        System.out.println(userEditPasswordRequestDTO);
        return ResponseEntity.ok(userEditFacade.editPassword(userEditPasswordRequestDTO));
    }

    @PostMapping("/params")
    public ResponseEntity<UserDTO> editParams(@RequestBody UserEditParamsRequest userEditParamsRequestDTO) {
        return ResponseEntity.ok(userEditFacade.editUserParams(userEditParamsRequestDTO));
    }



}
