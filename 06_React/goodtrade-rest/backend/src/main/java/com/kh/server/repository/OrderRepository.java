package com.kh.server.repository;

import com.kh.server.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByBuyer_Id(Long buyerId);
    
    List<Order> findByProduct_Id(Long productId);
}

