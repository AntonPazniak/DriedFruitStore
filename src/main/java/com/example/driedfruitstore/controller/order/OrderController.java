package com.example.driedfruitstore.controller.order;

import com.example.driedfruitstore.service.facade.order.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;



}
