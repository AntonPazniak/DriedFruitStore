package com.example.driedfruitstore.controller.order;

import com.example.driedfruitstore.model.dto.order.OrderDto;
import com.example.driedfruitstore.service.facade.order.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(Long orderId) {
        return orderFacade.getOrderById(orderId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllUsersOrders() {
        return orderFacade.findAllByUser();
    }




}
