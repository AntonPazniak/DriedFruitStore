package com.example.driedfruitstore.controller.auth;


import com.example.driedfruitstore.model.dto.request.AuthentificationRequest;
import com.example.driedfruitstore.model.dto.response.AuthentificationResponseDTO;
import com.example.driedfruitstore.model.dto.request.UserRegisterRequest;
import com.example.driedfruitstore.service.impl.auth.AuthentificationService;
import com.example.driedfruitstore.service.impl.auth.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthentificationService authentificationService;
    private final LogoutService logoutService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthentificationResponseDTO register(@RequestBody UserRegisterRequest requestDTO){
        return authentificationService.register(requestDTO);
    }

    @PostMapping("/authentification")
    @ResponseStatus(HttpStatus.OK)
    public AuthentificationResponseDTO authentification(@RequestBody AuthentificationRequest requestDTO){
        return authentificationService.authenticate(requestDTO);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logoutService.logout(request, response, authentication);
    }

}
