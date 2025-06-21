package com.example.driedfruitstore.model.dto.order;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(Long id, List<OrderItemDTO>items, LocalDateTime created) {
}
