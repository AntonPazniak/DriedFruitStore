package com.example.driedfruitstore.controller.product;

import com.example.driedfruitstore.dto.product.ProductDTO;
import com.example.driedfruitstore.service.facade.product.ProductFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductFacadeImpl productFacade;

    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> getProductsList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                productFacade.getPageOfProducts(page, size).getContent()
        );
    }

    @GetMapping("/")
    public ResponseEntity<ProductDTO> getProductById(@RequestParam Long id) {
        return ResponseEntity.ok(
                productFacade.getProductById(id)
        );

    }


}
