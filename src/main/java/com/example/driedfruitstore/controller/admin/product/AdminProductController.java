package com.example.driedfruitstore.controller.admin.product;

import com.example.driedfruitstore.model.dto.product.ProductAdminDTO;
import com.example.driedfruitstore.model.dto.request.EditProductRequest;
import com.example.driedfruitstore.model.dto.request.NewProductRequest;
import com.example.driedfruitstore.service.facade.admin.product.AdminProductFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/product")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductFacadeImpl adminProductFacade;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductAdminDTO getProduct(@PathVariable Long id) {
        return adminProductFacade.getProductById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductAdminDTO createNewProduct(@RequestBody NewProductRequest request){
        return adminProductFacade.createProduct(request);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ProductAdminDTO updateProduct(@RequestBody EditProductRequest request){
        return adminProductFacade.updateProduct(request);
    }

}
