package com.example.driedfruitstore.controller.admin.product;

import com.example.driedfruitstore.model.dto.product.ProductAdminDTO;
import com.example.driedfruitstore.model.dto.request.EditProductRequest;
import com.example.driedfruitstore.model.dto.request.NewProductRequest;
import com.example.driedfruitstore.service.facade.admin.product.AdminProductFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/product")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductFacadeImpl adminProductFacade;

    @GetMapping("/{id}")
    public ResponseEntity<ProductAdminDTO> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(
                adminProductFacade.getProductById(id)
        );
    }

    @PostMapping("/")
    public ResponseEntity<ProductAdminDTO> createNewProduct(@RequestBody NewProductRequest request){
        return ResponseEntity.ok(
                adminProductFacade.createProduct(request)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<ProductAdminDTO> updateProduct(@RequestBody EditProductRequest request){
        return ResponseEntity.ok(
                adminProductFacade.updateProduct(request)
        );
    }

}
