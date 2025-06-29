package com.example.driedfruitstore.mapper;


import com.example.driedfruitstore.model.dto.product.ProductAdminDTO;
import com.example.driedfruitstore.model.dto.product.ProductDTO;
import com.example.driedfruitstore.model.dto.request.NewProductRequest;
import com.example.driedfruitstore.model.entity.product.Product;
import com.example.driedfruitstore.service.inte.product.ProductCategoryService;
import com.example.driedfruitstore.service.inte.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAdminMapper {

    private final ProductCategoryService productCategoryService;
    private final ProductMapper productMapper;

    public ProductAdminDTO toDTO(Product product) {
        return ProductAdminDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .category(product.getCategory().getName().name())
                .created(product.getCreateDate())      // обязательно добавь
                .modified(product.getUpdateDate())    // обязательно добавь
                .build();
    }


    public Product fromDTO(NewProductRequest dto){
        return Product.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .category(productCategoryService.findByName(dto.category()))
                .build();
    }


}
