package com.lab.performance_lab.service;

import com.lab.performance_lab.model.User;
import com.lab.performance_lab.model.Order;
import com.lab.performance_lab.repository.UserRepository;
import com.lab.performance_lab.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Page<Order> getUserOrdersPaged(Long userId, Pageable pageable) {
        return orderRepository.findByUserId(userId, pageable);
    }
}
