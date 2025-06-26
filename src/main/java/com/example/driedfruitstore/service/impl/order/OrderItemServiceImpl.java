package com.example.driedfruitstore.service.impl.order;

import com.example.driedfruitstore.repository.order.OrderItemRepository;
import com.example.driedfruitstore.service.inte.order.OrderItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService{

    private final OrderItemRepository orderItemRepository;

}
