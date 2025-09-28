package com.lab.performance_lab.repository;

import com.lab.performance_lab.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByInStock(int inStock);

    @Query("SELECT p FROM Product p" +
            " WHERE (:category IS NULL OR p.category = :category)" +
            " AND (:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Product> findByCategoryAndPriceRange(
            @Param("category") String category,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
}
