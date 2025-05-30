package com.example.driedfruitstore.repository.auth;

import com.example.driedfruitstore.model.entity.auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    List<Token> findAllValidTokenByUser_Id(Long id);

    Optional<Token> findByToken(String token);
}
