package com.example.driedfruitstore.service.facade.order;


import com.example.driedfruitstore.model.dto.order.OrderDto;
import com.example.driedfruitstore.service.impl.auth.AuthUser;
import com.example.driedfruitstore.service.inte.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final AuthUser authUser;

    public OrderDto createOrder() {
        return orderService.createOrder(authUser.getAuthenticatedUser());
    }

    public OrderDto getOrderById(Long orderId) {
        return orderService.getUserOrderById(authUser.getAuthenticatedUser(), orderId);
    }

    public List<OrderDto> findAllByUser() {
        return orderService.findAllByUser(authUser.getAuthenticatedUser());
    }

}
