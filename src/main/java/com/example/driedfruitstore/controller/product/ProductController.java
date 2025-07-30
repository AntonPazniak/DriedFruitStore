package com.example.driedfruitstore.controller.product;

import com.example.driedfruitstore.model.dto.product.ProductDTO;
import com.example.driedfruitstore.service.facade.product.ProductFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class   ProductController {

    private final ProductFacadeImpl productFacade;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getProductsList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return productFacade.getPageOfProducts(page, size).getContent();
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@RequestParam Long id) {
        return productFacade.getProductById(id);
    }


}
