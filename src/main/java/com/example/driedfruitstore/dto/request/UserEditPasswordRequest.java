package com.example.driedfruitstore.dto.request;

public record UserEditPasswordRequest (String oldPassword, String newPassword) {
}
