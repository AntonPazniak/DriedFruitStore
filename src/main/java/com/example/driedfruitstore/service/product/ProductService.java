package com.example.driedfruitstore.service.product;

import com.example.driedfruitstore.model.dto.product.ProductDTO;
import com.example.driedfruitstore.model.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDTO getProductDTOById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO editProduct(ProductDTO productDTO);
    void deleteProductById(Long id);
    Page<ProductDTO> getProducts(Pageable pageable);
    Product getProductById(Long id);

}
