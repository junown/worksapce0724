package com.kh.server.repository;

import com.kh.server.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    
    Product save(Product product);
    
    List<Product> findAll();
    
    Optional<Product> findById(Long id);
    
    void delete(Product product);
    
    List<Product> findByCategory(String category);
    
    List<Product> findByStatus(String status);
    
    List<Product> findByCategoryAndStatus(String category, String status);
    
    List<Product> findBySeller_Id(Long sellerId);
    
    List<Product> findByNameContaining(String name);
}

