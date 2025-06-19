package com.example.driedfruitstore.model.dto.cart;

import com.example.driedfruitstore.model.dto.product.ProductDTO;

import java.io.Serializable;

public record CartItemDTO(int quantity, ProductDTO product) implements Serializable {
}
