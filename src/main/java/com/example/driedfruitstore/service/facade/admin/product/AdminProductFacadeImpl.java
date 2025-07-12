package com.example.driedfruitstore.service.facade.admin.product;


import com.example.driedfruitstore.model.dto.product.ProductAdminDTO;
import com.example.driedfruitstore.model.dto.request.EditProductRequest;
import com.example.driedfruitstore.model.dto.request.NewProductRequest;
import com.example.driedfruitstore.service.impl.auth.AuthUser;
import com.example.driedfruitstore.service.inte.admin.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductFacadeImpl {

    private final AdminProductService adminProductService;
    private final AuthUser authUser;

    public ProductAdminDTO getProductById(Long id){
        authUser.getAuthenticatedAdmin();
        return adminProductService.getProduct(id);
    }

    public ProductAdminDTO createProduct(NewProductRequest newProductRequest){
        authUser.getAuthenticatedAdmin();
        return adminProductService.createNewProduct(newProductRequest);
    }

    public ProductAdminDTO updateProduct(EditProductRequest request){
        authUser.getAuthenticatedAdmin();
        return adminProductService.updateProduct(request);
    }

}
