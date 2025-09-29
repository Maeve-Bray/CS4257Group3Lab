package com.lab.performance_lab.controller;

import com.lab.performance_lab.model.Product;
import com.lab.performance_lab.service.ProductService;
import com.lab.performance_lab.metrics.*;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;
    private final ProductMetrics productMetrics;

    public ProductController(ProductService productService, ProductMetrics productMetrics) {
        this.productService = productService;
        this.productMetrics = productMetrics;
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
    @Timed(value = "price_range_find_time", description = "Time taken to find products by price range")
    public List<Product> getByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return productService.findByPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/filter")
    public List<Product> getFilteredProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        productMetrics.incrementFilterRequest();
        return productService.getFilteredProducts(category, minPrice, maxPrice);
    }
}
