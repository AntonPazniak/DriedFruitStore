package com.example.driedfruitstore.service.cart;

import com.example.driedfruitstore.model.dto.cart.CartDTO;
import com.example.driedfruitstore.model.entity.User;
import com.example.driedfruitstore.model.entity.cart.Cart;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

public interface CartService {
    CartDTO getUsersCart(User user);
    CartDTO putItemToCart(User user, Long itemId, Integer quantity);
    CartDTO removeItemFromCart(User user, Long itemId);
    CartDTO cleanCart(User user);

}
