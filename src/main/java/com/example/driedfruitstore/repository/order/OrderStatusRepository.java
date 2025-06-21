package com.example.driedfruitstore.repository.order;

import com.example.driedfruitstore.model.emuns.OrderStatusEnum;
import com.example.driedfruitstore.model.entity.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    Optional<OrderStatus> findByName(OrderStatusEnum name);
}
