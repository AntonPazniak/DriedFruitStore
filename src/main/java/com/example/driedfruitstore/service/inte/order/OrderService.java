package com.example.driedfruitstore.service.inte.order;

import com.example.driedfruitstore.model.dto.order.OrderDto;
import com.example.driedfruitstore.model.entity.User;

import java.util.List;

public interface OrderService {

    OrderDto getUserOrderById(User user, Long orderId);
    OrderDto createOrder(User user);
    List<OrderDto> findAllByUser(User user);

}
