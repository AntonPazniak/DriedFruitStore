package com.example.driedfruitstore.service.facade.product;


import com.example.driedfruitstore.model.dto.product.ProductDTO;
import com.example.driedfruitstore.service.auth.AuthUser;
import com.example.driedfruitstore.service.product.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFacadeImpl {

    private final AuthUser  authUser;
    private final ProductService productService;

    public Page<ProductDTO> getPageOfProducts(int pageNumber, int pageSize) {
        return productService.getProducts(PageRequest.of(pageNumber, pageSize));
    }

    public ProductDTO getProductById(Long id) {
        return productService.getProductDTOById(id);
    }




}
