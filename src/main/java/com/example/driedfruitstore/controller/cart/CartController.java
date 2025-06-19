package com.example.driedfruitstore.controller.cart;

import com.example.driedfruitstore.model.dto.cart.CartDTO;
import com.example.driedfruitstore.service.facade.CartFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
class CartController {

    private final CartFacadeImpl  cartFacade;

    @GetMapping("/")
    public ResponseEntity<CartDTO> getUserCart(){
        return ResponseEntity.ok(cartFacade.getUserCart());
    }

    @PostMapping("/put")
    public ResponseEntity<CartDTO> putUserCart(@RequestParam Long productId, @RequestParam Integer quantity){
        return ResponseEntity.ok(cartFacade.putItemToCart(productId, quantity));
    }

    @PostMapping("/remove")
    public ResponseEntity<CartDTO> removeItemFromCart(@RequestParam Long productId){
        return  ResponseEntity.ok(cartFacade.removeItemFromCart(productId));
    }

    @PostMapping("/clean")
    public ResponseEntity<CartDTO> cleanCart(){
        return ResponseEntity.ok(cartFacade.clearCart());
    }


}
