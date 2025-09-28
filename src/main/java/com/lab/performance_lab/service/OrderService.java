package com.lab.performance_lab.service;

import com.lab.performance_lab.model.DTO.OrderInfoDto;
import com.lab.performance_lab.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderInfoDto> getOrderInfo() {
        return orderRepository.getOrderInfo();
    }
}
