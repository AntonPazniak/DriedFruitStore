package com.example.driedfruitstore.repository.order;

import com.example.driedfruitstore.model.entity.order.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
}
