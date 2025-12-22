package com.kh.server.repository;

import com.kh.server.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByCategory(String category);
    
    List<Product> findByStatus(String status);
    
    List<Product> findByCategoryAndStatus(String category, String status);
    
    List<Product> findBySeller_Id(Long sellerId);
    
    List<Product> findByNameContaining(String name);
}

