package com.example.driedfruitstore.service.impl.admin;

import com.example.driedfruitstore.exception.NotFoundException;
import com.example.driedfruitstore.mapper.ProductAdminMapper;
import com.example.driedfruitstore.mapper.ProductMapper;
import com.example.driedfruitstore.model.dto.product.ProductAdminDTO;
import com.example.driedfruitstore.model.dto.request.EditProductRequest;
import com.example.driedfruitstore.model.dto.request.NewProductRequest;
import com.example.driedfruitstore.model.entity.product.Product;
import com.example.driedfruitstore.repository.product.ProductRepository;
import com.example.driedfruitstore.service.inte.admin.AdminProductService;
import com.example.driedfruitstore.service.inte.product.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class AdminProductServiceImpl implements AdminProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final ProductAdminMapper productMapper;


    @Override
    public ProductAdminDTO createNewProduct(NewProductRequest request) {
        return productMapper.toDTO(
                productRepository.save(
                        productMapper.fromDTO(request)
                ));
    }

    @Override
    public ProductAdminDTO getProduct(Long id) {
        return productMapper.toDTO(
                getProductByIdOrThrow(id)
        );
    }

    @Override
    public ProductAdminDTO updateProduct(EditProductRequest request) {
        var product = getProductByIdOrThrow(request.id());
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setCategory(productCategoryService.findByName(request.category()));
        return productMapper.toDTO(
                productRepository.save(product)
        );
    }

    private Product getProductByIdOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Product not found with id: " + id)
                );
    }


}
