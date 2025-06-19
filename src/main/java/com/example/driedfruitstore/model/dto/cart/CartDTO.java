package com.example.driedfruitstore.model.dto.cart;

import com.example.driedfruitstore.model.dto.product.ProductDTO;

import java.io.Serializable;
import java.util.List;

public record CartDTO(List<CartItemDTO> items) implements Serializable {

}
