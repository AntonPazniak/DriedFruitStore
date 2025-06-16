package com.example.driedfruitstore.service.product;


import com.example.driedfruitstore.model.entity.product.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findById(Long id);
    ProductCategory findByName(String name);
    ProductCategory create(String name);
    List<ProductCategory> findAll();
}
