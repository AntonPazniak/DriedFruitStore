package com.example.driedfruitstore.controller.order;

import com.example.driedfruitstore.model.dto.order.OrderDto;
import com.example.driedfruitstore.service.facade.order.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

    @GetMapping("/")
    public ResponseEntity<OrderDto> getOrderById(Long orderId) {
        return ResponseEntity.ok(
                orderFacade.getOrderById(orderId)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllUsersOrders() {
        return ResponseEntity.ok(
                orderFacade.findAllByUser()
        );
    }




}
