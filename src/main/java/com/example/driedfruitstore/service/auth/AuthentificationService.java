package com.example.driedfruitstore.service.auth;

import com.example.driedfruitstore.dto.AuthentificationRequestDTO;
import com.example.driedfruitstore.dto.AuthentificationResponseDTO;
import com.example.driedfruitstore.dto.RegisterRequestDTO;
import com.example.driedfruitstore.model.emuns.RoleEnum;
import com.example.driedfruitstore.model.entity.Role;
import com.example.driedfruitstore.model.entity.User;
import com.example.driedfruitstore.repository.UserRepository;
import com.example.driedfruitstore.service.RoleService;
import com.example.driedfruitstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;


import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthentificationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthentificationResponseDTO oAuthenticate(Map<String, Object> attributes){
        String email = (String) attributes.get("email");
        User user = userService.findByEmail(email).orElseGet(
                () ->{
                    User newUser =  User.builder()
                            .email(email)
                            .firstName(attributes.get("name").toString())
                            .roles(List.of(roleService.getRole(RoleEnum.USER)))
                            .build();
                    return userRepository.save(newUser);
                }
        );

        return new AuthentificationResponseDTO(
                jwtService.generateToken(user)
        );
    }

    public AuthentificationResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        User user = User.builder()
                .email(registerRequestDTO.email())
                .password(passwordEncoder.encode(registerRequestDTO.password()))
                .firstName(registerRequestDTO.firstName())
                .lastName(registerRequestDTO.lastName())
                .roles(List.of(roleService.getRole(RoleEnum.USER)))
                .build();
        userRepository.save(user);
        return new AuthentificationResponseDTO(
                jwtService.generateToken(user)
        );
    }

    public AuthentificationResponseDTO authenticate(AuthentificationRequestDTO authentificationRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authentificationRequestDTO.email(),
                        authentificationRequestDTO.password()
                )
        );
        User user = userRepository.findByEmail(authentificationRequestDTO.email()).orElseThrow();
        return new AuthentificationResponseDTO(
                jwtService.generateToken(user)
        );
    }

}
