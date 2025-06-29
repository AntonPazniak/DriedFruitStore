package com.example.driedfruitstore.service.impl.auth;


import com.example.driedfruitstore.exception.ForbiddenException;
import com.example.driedfruitstore.exception.UnauthorizedException;
import com.example.driedfruitstore.model.emuns.RoleEnum;
import com.example.driedfruitstore.model.entity.Role;
import com.example.driedfruitstore.model.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUser {

    private final EntityManager entityManager;

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() instanceof String) {
            throw new UnauthorizedException("You are not logged in.");
        }

        // Вытаскиваем ID
        Long userId = ((User) authentication.getPrincipal()).getId();

        // Возвращаем "ленивую" ссылку на User — Hibernate не будет делать SELECT
        return entityManager.getReference(User.class, userId);
    }

    public User  getAuthenticatedModer() {
        User currentUser = getAuthenticatedUser();
        if(currentUser.getRoles().stream()
                .map(Role::getName)
                .anyMatch(role -> role == RoleEnum.MODERATOR || role == RoleEnum.ADMIN)
        )
            return currentUser;
        else
            throw new ForbiddenException("You don't have permission to perform this action.");
    }


}
