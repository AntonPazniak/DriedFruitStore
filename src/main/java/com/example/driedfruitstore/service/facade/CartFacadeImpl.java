package com.example.driedfruitstore.service.facade;

import com.example.driedfruitstore.model.dto.cart.CartDTO;
import com.example.driedfruitstore.service.auth.AuthUser;
import com.example.driedfruitstore.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartFacadeImpl {

    private final CartService cartService;
    private final AuthUser  authUser;


    public CartDTO getUserCart(){
        return cartService.getUsersCart(authUser.getAuthenticatedUser());
    }

    public CartDTO putItemToCart(Long productId, Integer quantity){
        return cartService.putItemToCart(authUser.getAuthenticatedUser(), productId, quantity);
    }

    public CartDTO removeItemFromCart(Long productId){
        return cartService.removeItemFromCart(authUser.getAuthenticatedUser(),productId);
    }

    public CartDTO clearCart(){
        return cartService.cleanCart(authUser.getAuthenticatedUser());
    }

}
