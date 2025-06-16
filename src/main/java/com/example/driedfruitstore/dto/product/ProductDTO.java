package com.example.driedfruitstore.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private String category;
    private String image;
}
