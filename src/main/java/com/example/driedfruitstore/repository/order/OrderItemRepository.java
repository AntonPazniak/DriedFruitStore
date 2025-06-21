package com.example.driedfruitstore.repository.order;

import com.example.driedfruitstore.model.entity.order.item.OrderItem;
import com.example.driedfruitstore.model.entity.order.item.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
