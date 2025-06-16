package com.example.driedfruitstore.repository.product;

import com.example.driedfruitstore.model.emuns.ProductCategoryEnum;
import com.example.driedfruitstore.model.entity.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
    Optional<ProductCategory> findByName(ProductCategoryEnum name);
}
