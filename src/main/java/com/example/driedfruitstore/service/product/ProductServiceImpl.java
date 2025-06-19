package com.example.driedfruitstore.service.product;

import com.example.driedfruitstore.model.dto.product.ProductDTO;
import com.example.driedfruitstore.exception.NotFoundException;
import com.example.driedfruitstore.mapper.ProductMapper;
import com.example.driedfruitstore.model.entity.product.Product;
import com.example.driedfruitstore.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final ProductMapper productMapper;

    @Override
    public ProductDTO getProductDTOById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product not found with id: " + id)
        ));
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        var product = productRepository.save(
                Product.builder()
                        .name(productDTO.getName())
                        .description(productDTO.getDescription())
                        .price(productDTO.getPrice())
                        .image(productDTO.getImage())
                        .category(productCategoryService.findByName(productDTO.getCategory()))
                        .build()
        );
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO editProduct(ProductDTO productDTO) {
        return productMapper
                .toDto(productRepository
                        .save(productMapper.fromDto(productDTO)
                        ));
    }
 
    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product not found with id: " + id)
        );
        productRepository.delete(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product not found with id: " + id)
        );
    }

    @Override
    public Page<ProductDTO> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

}
