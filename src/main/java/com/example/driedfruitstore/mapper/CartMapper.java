package com.example.driedfruitstore.mapper;


import com.example.driedfruitstore.model.dto.cart.CartDTO;
import com.example.driedfruitstore.model.dto.cart.CartItemDTO;
import com.example.driedfruitstore.model.entity.cart.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final ProductMapper productMapper;

    public CartDTO toDto(Cart cart) {
        return new CartDTO(
                cart.getItems().stream()
                        .map(e -> new CartItemDTO(e.getQuantity(), productMapper.toDto(e.getProduct())))
                        .toList()
        );
    }

}
