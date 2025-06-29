package com.example.driedfruitstore.model.dto.request;

public record EditProductRequest(
        Long id,
        String name,
        String description,
        Float price,
        String category
) {
}
