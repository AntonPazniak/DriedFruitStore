package com.example.driedfruitstore.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EditProductRequest(
        @NotBlank
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotBlank
        Float price,
        @NotBlank
        String category
) {
}
