package com.example.driedfruitstore.controller.admin;

import com.example.driedfruitstore.model.dto.product.ProductAdminDTO;
import com.example.driedfruitstore.model.dto.request.EditProductRequest;
import com.example.driedfruitstore.model.dto.request.NewProductRequest;
import com.example.driedfruitstore.service.facade.admin.AdminProductFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/product")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductFacadeImpl adminProductFacade;

    @GetMapping("/")
    public ResponseEntity<ProductAdminDTO> getProduct(Long id) {
        return ResponseEntity.ok(
                adminProductFacade.getProductById(id)
        );
    }

    @PostMapping("/new")
    public ResponseEntity<ProductAdminDTO> createNewProduct(@RequestBody NewProductRequest request){
        return ResponseEntity.ok(
                adminProductFacade.createProduct(request)
        );
    }

    @PostMapping("/update")
    public ResponseEntity<ProductAdminDTO> updateProduct(@RequestBody EditProductRequest request){
        return ResponseEntity.ok(
                adminProductFacade.updateProduct(request)
        );
    }

}
