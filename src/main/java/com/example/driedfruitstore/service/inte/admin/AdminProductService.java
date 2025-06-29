package com.example.driedfruitstore.service.inte.admin;

import com.example.driedfruitstore.model.dto.product.ProductAdminDTO;
import com.example.driedfruitstore.model.dto.product.ProductDTO;
import com.example.driedfruitstore.model.dto.request.EditProductRequest;
import com.example.driedfruitstore.model.dto.request.NewProductRequest;

public interface AdminProductService {

    ProductAdminDTO createNewProduct(NewProductRequest request);
    ProductAdminDTO getProduct(Long id);
    ProductAdminDTO updateProduct(EditProductRequest request);

}
