package com.lab.performance_lab.controller;

import com.lab.performance_lab.model.DTO.OrderInfoDto;
import com.lab.performance_lab.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orderInfo")
    public List<OrderInfoDto> getOrderInfo() {
        return orderService.getOrderInfo();
    }
}
