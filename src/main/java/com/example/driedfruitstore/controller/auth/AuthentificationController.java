package com.example.driedfruitstore.controller.auth;


import com.example.driedfruitstore.dto.request.AuthentificationRequest;
import com.example.driedfruitstore.dto.response.AuthentificationResponseDTO;
import com.example.driedfruitstore.dto.request.RegisterRequest;
import com.example.driedfruitstore.service.auth.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthentificationService authentificationService;

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

}
