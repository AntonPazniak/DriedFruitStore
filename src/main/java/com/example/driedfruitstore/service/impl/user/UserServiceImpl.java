package com.example.driedfruitstore.service.impl.user;

import com.example.driedfruitstore.model.dto.request.UserEditParamsRequest;
import com.example.driedfruitstore.model.dto.request.UserRegisterRequest;
import com.example.driedfruitstore.model.dto.user.UserDTO;
import com.example.driedfruitstore.exception.NotFoundException;
import com.example.driedfruitstore.mapper.UserDTOMapper;
import com.example.driedfruitstore.model.emuns.RoleEnum;
import com.example.driedfruitstore.model.entity.User;
import com.example.driedfruitstore.repository.user.UserRepository;
import com.example.driedfruitstore.service.inte.user.RoleService;
import com.example.driedfruitstore.service.inte.user.UserAuthenticationService;
import com.example.driedfruitstore.service.inte.user.UserEditPermissionService;
import com.example.driedfruitstore.service.inte.user.UserEditService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserAuthenticationService, UserEditService, UserEditPermissionService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserDTOMapper userDTOMapper;
    private final RoleService roleService;


    @Override
    public User getUserByLoginOrThrow(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(
                        ()->new NotFoundException("User not found")
                );
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDTO editPassword(User user, String oldPassword, String newPassword) {
        if (!encoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Старый пароль введён неверно");
        }
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);

        return userDTOMapper.toDTO(user);
    }

    @Override
    public UserDTO editUser(User user, UserEditParamsRequest dto) {
        user.setLogin(dto.login());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        return userDTOMapper.toDTO(userRepository.save(user));
    }



    @Override
    public User createUser(UserRegisterRequest userRegisterRequest) {
        return
                userRepository.save(
                        User.builder()
                                .email(userRegisterRequest.email())
                                .login(userRegisterRequest.login())
                                .password(encoder.encode(userRegisterRequest.password()))
                                .firstName(userRegisterRequest.firstName())
                                .lastName(userRegisterRequest.lastName())
                                .roles(Set.of(roleService.getRole(RoleEnum.USER)))
                                .build()
                );
    }

    @Override
    public User createUser(Map<String, Object> attributes) {
        String email = (String) attributes.get("email");
        String login =
                (attributes.get("login") != null) ? attributes.get("login").toString()
                        : (attributes.get("preferred_username") != null) ? attributes.get("preferred_username").toString()
                        : (attributes.get("name") != null) ? attributes.get("name").toString().split(" ")[0]
                        : email.split("@")[0];

        return userRepository.save(
                User.builder()
                        .email(email)
                        .login(login)
                        .firstName(attributes.get("name").toString())
                        .roles(Set.of(roleService.getRole(RoleEnum.USER)))
                        .build()
        );

    }


}
