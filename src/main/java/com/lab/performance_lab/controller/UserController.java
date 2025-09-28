package com.lab.performance_lab.controller;

import com.lab.performance_lab.model.User;
import com.lab.performance_lab.model.Order;
import com.lab.performance_lab.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/orders")
    public List<Order> getOrders(@PathVariable Long id) {
        return userService.getUserOrders(id);
    }

    @GetMapping("/{id}/orders/page")
    public Page<Order> getOrdersPaged(@PathVariable Long id, Pageable pageable) {
        return userService.getUserOrdersPaged(id, pageable);
    }

    @GetMapping("/filter")
    public List<User> getFilteredUsers(
            @RequestParam String status,
            @RequestParam Timestamp dateFrom,
            @RequestParam Timestamp dateTo
    ) {
        return userService.getFilteredUsers(status, dateFrom, dateTo);
    }
}
