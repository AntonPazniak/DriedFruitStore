package com.example.driedfruitstore.model.dto.user;

import java.util.List;

public record UserRoleDTO(
        String login,
        List<String> roles
) {
}
