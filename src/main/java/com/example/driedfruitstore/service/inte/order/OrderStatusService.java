package com.example.driedfruitstore.service.inte.order;

import com.example.driedfruitstore.model.emuns.OrderStatusEnum;
import com.example.driedfruitstore.model.entity.order.OrderStatus;

public interface OrderStatusService {
    OrderStatus getByName(String name);
}
