package com.example.driedfruitstore.model.dto.request;

public record NewProductRequest(
        String name,
        String description,
        Float price,
        String category
) {
}
