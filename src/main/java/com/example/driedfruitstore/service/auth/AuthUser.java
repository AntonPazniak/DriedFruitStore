package com.example.driedfruitstore.service.auth;


import com.example.driedfruitstore.exception.UnauthorizedException;
import com.example.driedfruitstore.model.emuns.RoleEnum;
import com.example.driedfruitstore.model.entity.Role;
import com.example.driedfruitstore.model.entity.User;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthUser {

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof User user)) {
            throw new UnauthorizedException("You are not authenticated.");
        }
        return user;
    }

    public boolean isModerAuthenticatedUser() {
        User currentUser = getAuthenticatedUser();
        return currentUser.getRoles().stream()
                .map(Role::getName)
                .anyMatch(role -> role == RoleEnum.MODERATOR || role == RoleEnum.ADMIN);
    }


}
