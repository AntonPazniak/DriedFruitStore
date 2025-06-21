package com.example.driedfruitstore.model.dto.order;

import com.example.driedfruitstore.model.dto.product.ProductDTO;

public record OrderItemDTO(ProductDTO product, Integer quantity) {
}
