package com.kh.server.repository;

import com.kh.server.entity.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    
    Order save(Order order);
    
    Optional<Order> findById(Long id);
    
    List<Order> findAll();
    
    void delete(Order order);
    
    List<Order> findByBuyer_Id(Long buyerId);
    
    List<Order> findByProduct_Id(Long productId);
}

