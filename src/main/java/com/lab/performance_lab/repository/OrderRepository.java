package com.lab.performance_lab.repository;

import com.lab.performance_lab.model.DTO.OrderInfoDto;
import com.lab.performance_lab.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query method to find all orders by userId
    List<Order> findByUserId(Long userId);
    Page<Order> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT p.name, u.username, o.totalAmount from User u inner join Order o on u.id = o.userId inner join Product p on p.id = o.productId")
    List<OrderInfoDto> getOrderInfo();
}
