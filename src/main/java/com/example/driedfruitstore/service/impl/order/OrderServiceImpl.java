package com.example.driedfruitstore.service.impl.order;


import com.example.driedfruitstore.exception.BadRequestException;
import com.example.driedfruitstore.exception.ForbiddenException;
import com.example.driedfruitstore.exception.NotFoundException;
import com.example.driedfruitstore.mapper.OrderMapper;
import com.example.driedfruitstore.model.dto.order.OrderDto;
import com.example.driedfruitstore.model.emuns.OrderStatusEnum;
import com.example.driedfruitstore.model.entity.User;
import com.example.driedfruitstore.model.entity.cart.Cart;
import com.example.driedfruitstore.model.entity.order.Order;
import com.example.driedfruitstore.model.entity.order.item.OrderItem;
import com.example.driedfruitstore.model.entity.order.item.OrderItemId;
import com.example.driedfruitstore.repository.order.OrderItemRepository;
import com.example.driedfruitstore.repository.order.OrderRepository;
import com.example.driedfruitstore.service.inte.cart.CartService;
import com.example.driedfruitstore.service.inte.order.OrderService;
import com.example.driedfruitstore.service.inte.order.OrderStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderStatusService orderStatusService;
    private final CartService cartService;
    private final OrderMapper  orderMapper;

    @Override
    public OrderDto getUserOrderById(User user, Long orderId) {
        return orderMapper.toDto(getUsersOrderOrThrow(user, orderId));

    }

    @Override
    public OrderDto createOrder(User user) {
        return orderMapper.toDto(
                createOrderFromCartAndSave(cartService.getCart(user))
        );
    }

    private Order createOrderFromCartAndSave(Cart cart) {
        if (cart.getItems().isEmpty())
            throw new BadRequestException("Your cart is empty");

        Order order = orderRepository.save(Order.builder()
                .user(cart.getUser())
                .orderStatus(orderStatusService.getByName(OrderStatusEnum.NEW.name()))
                .build());

        order.setItems(
                orderItemRepository.saveAll(getOrderItemsFromCart(cart, order))
        );
        return order;
    }

    private List<OrderItem> getOrderItemsFromCart(Cart cart, Order order) {
        return cart.getItems().stream().map(
                e -> OrderItem.builder()
                        .id(new OrderItemId(order.getId(),e.getProduct().getId()))
                        .order(order)
                        .product(e.getProduct())
                        .quantity(e.getQuantity())
                        .build()
        ).toList();
    }

    private Order getUsersOrderOrThrow(User user, Long orderId) {
        Order order = getOrderByIdOrThrow(orderId);
        if (order.getUser().equals(user)) {
            return order;
        } else {
            throw new ForbiddenException("User not allowed to order");
        }
    }


    private Order getOrderByIdOrThrow(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("Order not found with id: " + orderId)
        );
    }


}
