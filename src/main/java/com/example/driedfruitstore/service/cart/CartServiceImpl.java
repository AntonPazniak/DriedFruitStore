package com.example.driedfruitstore.service.cart;

import com.example.driedfruitstore.mapper.CartMapper;
import com.example.driedfruitstore.model.dto.cart.CartDTO;
import com.example.driedfruitstore.model.entity.User;
import com.example.driedfruitstore.model.entity.cart.Cart;
import com.example.driedfruitstore.model.entity.cart.item.CartItem;
import com.example.driedfruitstore.model.entity.cart.item.CartItemId;
import com.example.driedfruitstore.model.entity.product.Product;
import com.example.driedfruitstore.repository.cart.CartRepository;
import com.example.driedfruitstore.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartMapper cartMapper;

    @Override
    public CartDTO getUsersCart(User user) {
        return cartMapper.toDto(getCartByUserId(user));
    }

    @Override
    public CartDTO putItemToCart(User user, Long itemId, Integer quantity) {
        Cart cart = getCartByUserId(user);
        var existingItem = getOptionalCartItem(cart, itemId);
        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(quantity);
        } else {
            Product product = productService.getProductById(itemId);
            cart.getItems().add(
                    CartItem.builder()
                            .id(new CartItemId(cart.getId(), product.getId()))
                            .product(product)
                            .cart(cart)
                            .quantity(quantity)
                            .build()
            );
        }
        return cartMapper.toDto(cartRepository.save(cart));
    }

    @Override
    public CartDTO removeItemFromCart(User user, Long itemId) {
        Cart cart = getCartByUserId(user);
        var existingItem = getOptionalCartItem(cart, itemId);
        existingItem.ifPresent(cartItem -> cart.getItems().remove(cartItem));
        return cartMapper.toDto(
                cartRepository.save(cart)
        );
    }

    @Override
    public CartDTO cleanCart(User user) {
        Cart cart = getCartByUserId(user);
        cart.getItems().clear();
        return cartMapper.toDto(cartRepository.save(cart));
    }

    private Optional<CartItem> getOptionalCartItem(Cart cart, Long itemId) {
        return cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(itemId))
                .findFirst();
    }

    private Cart getCartByUserId(User user) {
        return cartRepository.findById(user.getId())
                .orElseGet(() -> createEmptyCartForUser(user));
    }

    private Cart createEmptyCartForUser(User user) {
        return cartRepository.save(Cart.builder()
                .user(user)
                .items(new ArrayList<>())
                .build());
    }
}
