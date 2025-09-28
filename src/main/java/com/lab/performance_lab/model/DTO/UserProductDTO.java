package com.lab.performance_lab.model.DTO;

public class UserProductDTO {
    private String username;
    private String productName;

    public UserProductDTO (String username, String productName) {
        this.username = username;
        this.productName = productName;
    }

    public String getUsername() {
        return username;
    }

    public  String getProductName() {
        return productName;
    }
}
