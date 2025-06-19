package com.example.driedfruitstore.model.dto.request;

public record UserEditPasswordRequest (String oldPassword, String newPassword) {
}
