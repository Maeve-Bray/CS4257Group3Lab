package com.lab.performance_lab.controller;

import com.lab.performance_lab.model.Product;
import com.lab.performance_lab.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping("/getById")
    public Product getById(@RequestParam Long id) {
        return productService.findById(id);
    }

    @GetMapping("/getByCategory")
    public List<Product> getByCategory(@RequestParam String category) {
        return productService.findByCategory(category);
    }

    @GetMapping("/getByInStock")
    public List<Product> getByInStock(int inStock) {
        return productService.findByInStock(inStock);
    }

    @GetMapping("/getByPriceRange")
    public List<Product> getByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return productService.findByPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/filter")
    public List<Product> getFilteredProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        return productService.getFilteredProducts(category, minPrice, maxPrice);
    }
}
