package com.lab.performance_lab.controller;

import com.lab.performance_lab.model.User;
import com.lab.performance_lab.model.Order;
import com.lab.performance_lab.repository.UserRepository;
import com.lab.performance_lab.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public UserController(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    // GET /api/users/{id} → fetch a user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // GET /api/users/{id}/orders → fetch all orders for a user
    @GetMapping("/{id}/orders")
    public List<Order> getOrders(@PathVariable Long id) {
        return orderRepository.findByUserId(id);
    }

    @GetMapping("/{id}/orders/page")
    public Page<Order> getOrdersPaged(@PathVariable Long id,
                                      org.springframework.data.domain.Pageable pageable) {
        return orderRepository.findByUserId(id, pageable);
    }

}
