package com.example.driedfruitstore.dto.user;

public record UserEditDTO(String firstName, String lastName, String email, String oldPassword, String newPassword) {
}
