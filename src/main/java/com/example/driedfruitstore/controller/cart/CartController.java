package com.example.driedfruitstore.controller.cart;

import com.example.driedfruitstore.model.dto.cart.CartDTO;
import com.example.driedfruitstore.service.facade.CartFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
class CartController {

    private final CartFacadeImpl  cartFacade;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public CartDTO getUserCart(){
        return cartFacade.getUserCart();
    }

    @PostMapping("/put")
    @ResponseStatus(HttpStatus.OK)
    public CartDTO putUserCart(@RequestParam Long productId, @RequestParam Integer quantity){
        return cartFacade.putItemToCart(productId, quantity);
    }

    @PostMapping("/remove")
    @ResponseStatus(HttpStatus.OK)
    public CartDTO removeItemFromCart(@RequestParam Long productId){
        return  cartFacade.removeItemFromCart(productId);
    }

    @PostMapping("/clean")
    @ResponseStatus(HttpStatus.OK)
    public CartDTO cleanCart(){
        return cartFacade.clearCart();
    }


}
