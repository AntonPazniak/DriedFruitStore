package com.example.driedfruitstore.controller.auth;

import com.example.driedfruitstore.dto.response.AuthentificationResponseDTO;
import com.example.driedfruitstore.service.auth.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    private final AuthentificationService authentificationService;

    @GetMapping("/")
    public String index() {
            return "<a href='/oauth2/authorization/google'>Войти через Google</a>";
    }

    @GetMapping("/oauth2/success")
    public AuthentificationResponseDTO success(OAuth2AuthenticationToken token) {
          return authentificationService.oAuthenticate(token.getPrincipal().getAttributes());
    }
}
