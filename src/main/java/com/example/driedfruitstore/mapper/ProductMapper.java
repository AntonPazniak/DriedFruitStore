package com.example.driedfruitstore.mapper;

import com.example.driedfruitstore.model.dto.product.ProductDTO;
import com.example.driedfruitstore.model.entity.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDto(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .category(product.getCategory().getName().name())
                .build();
    }

    public Product fromDto(ProductDTO dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .image(dto.getImage())
                .build();
    }

}
