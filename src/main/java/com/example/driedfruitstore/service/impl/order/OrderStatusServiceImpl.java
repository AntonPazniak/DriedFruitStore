package com.example.driedfruitstore.service.impl.order;

import com.example.driedfruitstore.exception.BadRequestException;
import com.example.driedfruitstore.model.emuns.OrderStatusEnum;
import com.example.driedfruitstore.model.emuns.ProductCategoryEnum;
import com.example.driedfruitstore.model.entity.order.OrderStatus;
import com.example.driedfruitstore.repository.order.OrderStatusRepository;
import com.example.driedfruitstore.service.inte.order.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService, ApplicationRunner {

    private final OrderStatusRepository orderStatusRepository;
    private final Map<OrderStatusEnum, OrderStatus> orderStatusCache = new EnumMap<>(OrderStatusEnum.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            orderStatusRepository.findByName(orderStatusEnum).orElseGet(() ->
                    orderStatusRepository.save(OrderStatus.builder()
                            .name(orderStatusEnum)
                            .build()
                    )
            );
            orderStatusRepository.findAll().forEach(orderStatus ->
                    orderStatusCache.put(orderStatus.getName(), orderStatus)
            );
        }
    }


    @Override
    public OrderStatus getByName(String name) {
        try {
            return orderStatusCache.get(OrderStatusEnum.valueOf(name));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid category name: " + name);
        }
    }
}
