package com.example.driedfruitstore.service.impl.auth;

import com.example.driedfruitstore.model.dto.request.AuthentificationRequest;
import com.example.driedfruitstore.model.dto.response.AuthentificationResponseDTO;
import com.example.driedfruitstore.model.dto.request.UserRegisterRequest;
import com.example.driedfruitstore.exception.ForbiddenException;
import com.example.driedfruitstore.model.entity.auth.Token;
import com.example.driedfruitstore.model.emuns.TokenTypeEnum;
import com.example.driedfruitstore.model.entity.User;
import com.example.driedfruitstore.repository.auth.TokenRepository;
import com.example.driedfruitstore.service.inte.user.RoleService;
import com.example.driedfruitstore.service.inte.user.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;


import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthentificationService {

    private final UserAuthenticationService userAuthenticationService;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    public AuthentificationResponseDTO oAuthenticate(Map<String, Object> attributes) {
        String email = (String) attributes.get("email");

        User user = userAuthenticationService.findByEmail(email)
                .orElseGet(
                        () -> userAuthenticationService.createUser(attributes));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        String token = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, token);

        return new AuthentificationResponseDTO(token);
    }

    public AuthentificationResponseDTO register(UserRegisterRequest registerRequestDTO) {
        User user = userAuthenticationService.createUser(registerRequestDTO);
        String token = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, token);

        return new AuthentificationResponseDTO(token);
    }

    public AuthentificationResponseDTO authenticate(AuthentificationRequest authentificationRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authentificationRequestDTO.email(),
                        authentificationRequestDTO.password()
                )
        );
        User user = userAuthenticationService.findByEmail(authentificationRequestDTO.email())
                .orElseThrow(() -> new ForbiddenException("Email or password is incorrect"));

        String token = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, token);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);


        return new AuthentificationResponseDTO(token);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenTypeEnum.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser_Id(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
