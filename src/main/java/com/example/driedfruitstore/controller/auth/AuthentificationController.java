package com.example.driedfruitstore.controller.auth;


import com.example.driedfruitstore.model.dto.request.AuthentificationRequest;
import com.example.driedfruitstore.model.dto.response.AuthentificationResponseDTO;
import com.example.driedfruitstore.model.dto.request.RegisterRequest;
import com.example.driedfruitstore.service.auth.AuthentificationService;
import com.example.driedfruitstore.service.auth.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthentificationService authentificationService;
    private final LogoutService logoutService;

    @PostMapping("/register")
    public ResponseEntity<AuthentificationResponseDTO> register(
            @RequestBody RegisterRequest requestDTO
    ){
        return ResponseEntity.ok(authentificationService.register(requestDTO));
    }


    @PostMapping("/authentification")
    public ResponseEntity<AuthentificationResponseDTO> authentification(
            @RequestBody AuthentificationRequest requestDTO
    ){
        return ResponseEntity.ok(authentificationService.authenticate(requestDTO));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logoutService.logout(request, response, authentication);
        return ResponseEntity.noContent().build();
    }



}
