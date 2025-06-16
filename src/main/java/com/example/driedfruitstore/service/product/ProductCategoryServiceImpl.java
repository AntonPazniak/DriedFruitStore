package com.example.driedfruitstore.service.product;

import com.example.driedfruitstore.exception.BadRequestException;
import com.example.driedfruitstore.model.emuns.ProductCategoryEnum;
import com.example.driedfruitstore.model.entity.product.ProductCategory;
import com.example.driedfruitstore.repository.product.ProductCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService, ApplicationRunner {

    private final ProductCategoryRepository productCategoryRepository;
    private final Map<ProductCategoryEnum, ProductCategory> categoryCache = new EnumMap<>(ProductCategoryEnum.class);

    @Override
    public void run(ApplicationArguments args) {
        for (ProductCategoryEnum categoryEnum : ProductCategoryEnum.values()) {
            productCategoryRepository.findByName(categoryEnum).orElseGet(() -> {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setName(categoryEnum);
                return productCategoryRepository.save(productCategory);
            });
        }

        List<ProductCategory> categories = productCategoryRepository.findAll();
        categories.forEach(category -> categoryCache.put(category.getName(), category));
    }

    @Override
    public ProductCategory findById(Long id) {
        return null;
    }

    @Override
    public ProductCategory findByName(String name) {
        try {
            return categoryCache.get(ProductCategoryEnum.valueOf(name));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid category name: " + name);
        }
    }


    @Override
    public ProductCategory create(String name) {
        return null;
    }

    @Override
    public List<ProductCategory> findAll() {
        return new ArrayList<>(categoryCache.values());
    }




}
