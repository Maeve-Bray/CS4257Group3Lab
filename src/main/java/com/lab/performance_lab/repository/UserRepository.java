package com.lab.performance_lab.repository;

import com.lab.performance_lab.model.DTO.UserProductDTO;
import com.lab.performance_lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u" +
            " WHERE u.status = :status" +
            " AND u.createdAt BETWEEN :dateFrom AND :dateTo")
    List<User> findUsersByStatusAndDateRange(
            @Param("status") String status,
            @Param("dateFrom") Timestamp dateFrom,
            @Param("dateTo") Timestamp dateTo
    );

    @Query("SELECT DISTINCT u.username, p.name FROM User u JOIN Order o ON u.id=o.userId JOIN Product p ON p.id=o.productId")
    List<UserProductDTO> getUserProductInfo();
}
