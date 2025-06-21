package com.example.driedfruitstore.mapper;

import com.example.driedfruitstore.model.dto.order.OrderDto;
import com.example.driedfruitstore.model.dto.order.OrderItemDTO;
import com.example.driedfruitstore.model.entity.order.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderMapper {

    private final ProductMapper productMapper;

    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getItems().stream().map(e ->
                        new OrderItemDTO(
                                productMapper.toDto(e.getProduct()),
                                e.getQuantity()
                        )
                ).toList(),
                order.getCreateDate());
    }
}
