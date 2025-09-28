package com.lab.performance_lab.model.DTO;

import java.math.BigDecimal;

public class OrderInfoDto {
    private String productName;
    private String username;
    private BigDecimal totalAmount;

    public OrderInfoDto(String productName, String username, BigDecimal totalAmount) {
        this.productName = productName;
        this.username = username;
        this.totalAmount = totalAmount;
    }

    public String getProductName() {
        return productName;
    }

    public String getUsername() {
        return username;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
