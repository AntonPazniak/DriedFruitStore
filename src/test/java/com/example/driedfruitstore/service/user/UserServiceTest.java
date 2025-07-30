package com.example.driedfruitstore.service.user;

import com.example.driedfruitstore.exception.NotFoundException;
import com.example.driedfruitstore.mapper.UserDTOMapper;
import com.example.driedfruitstore.model.entity.User;
import com.example.driedfruitstore.repository.user.UserRepository;
import com.example.driedfruitstore.service.impl.user.UserServiceImpl;
import com.example.driedfruitstore.service.inte.user.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserDTOMapper userDTOMapper;

    @Mock
    private RoleService roleService;

    private final String testLogin = "testLogin";



    @Test
    void getUserByLoginOrThrow_shouldReturnUser_whenUserExists() {
        User user = new User();
        when(userRepository.findByLogin(testLogin)).thenReturn(Optional.of(user));

        User result = userService.getUserByLoginOrThrow(testLogin);

        assertEquals(user, result);
    }

    @Test
    void getUserByLoginOrThrow_shouldReturnUser_whenUserDoesNotExist() {
        when(userRepository.findByLogin(testLogin)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getUserByLoginOrThrow(testLogin));
    }

    @Test
    void findByEmail_shouldReturnUser_whenUserExists() {
        User user = new User();
        when(userRepository.findByEmail(testLogin)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail(testLogin);

        assertEquals(Optional.of(user), result);

    }




}
