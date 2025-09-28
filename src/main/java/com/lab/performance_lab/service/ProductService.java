package com.lab.performance_lab.service;

import com.lab.performance_lab.model.Product;
import com.lab.performance_lab.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductsRepository productsRepository;

    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product findById(Long id) {
        return productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public List<Product> findByInStock(int inStock) {
        if (inStock < 0 || inStock > 1) {
            throw new IllegalArgumentException("InStock must be 0 or 1");
        }

        return productsRepository.findByInStock(inStock);
    }

    public List<Product> findByCategory(String category) {
        if(category == null || category.isEmpty()){
            throw new IllegalArgumentException("Category cannot be null or empty");
        }

        return productsRepository.findByCategory(category);
    }

    public List<Product> findByPriceBetween(Double minPrice, Double maxPrice) {
        if (minPrice == null || minPrice < 0 || maxPrice == null || maxPrice < 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }

        return productsRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getFilteredProducts(
            String category,
            Double minPrice,
            Double maxPrice
    ) {
        if (minPrice == null || minPrice < 0 || maxPrice == null || maxPrice < 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }else if(category == null || category.isEmpty()){
            throw new IllegalArgumentException("Category cannot be null or empty");
        }

        return productsRepository.findByCategoryAndPriceRange(category, minPrice, maxPrice);
    }
}
