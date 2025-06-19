package com.example.driedfruitstore.service.user;

import com.example.driedfruitstore.model.dto.request.UserEditParamsRequest;
import com.example.driedfruitstore.model.dto.user.UserDTO;
import com.example.driedfruitstore.exception.NotFoundException;
import com.example.driedfruitstore.mapper.UserDTOMapper;
import com.example.driedfruitstore.model.entity.User;
import com.example.driedfruitstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserDTOMapper userDTOMapper;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
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

        return userDTOMapper.apply(user);
    }

    @Override
    public UserDTO editUser(User user, UserEditParamsRequest dto) {
        user.setLogin(dto.login());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        return userDTOMapper.apply(userRepository.save(user));
    }


    public UserDTO getUserById(Long id) {
        return userDTOMapper.apply(userRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("<UNK> <UNK> <UNK> <UNK>")
        ));
    }


}
