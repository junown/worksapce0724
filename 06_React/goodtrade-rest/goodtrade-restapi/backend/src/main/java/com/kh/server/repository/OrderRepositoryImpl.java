package com.kh.server.repository;

import com.kh.server.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Order save(Order order) {
        if (order.getId() == null) {
            // 새 엔티티인 경우 INSERT
            em.persist(order);
        } else {
            // 기존 엔티티인 경우 UPDATE
            order = em.merge(order);
        }
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Order order = em.find(Order.class, id);
        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> findAll() {
        String jpql = "SELECT o FROM Order o";
        return em.createQuery(jpql, Order.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Order order) {
        if (em.contains(order)) {
            em.remove(order);
        } else {
            em.remove(em.merge(order));
        }
    }

    @Override
    public List<Order> findByBuyer_Id(Long buyerId) {
        String jpql = "SELECT o FROM Order o WHERE o.buyer.id = :buyerId";
        return em.createQuery(jpql, Order.class)
                .setParameter("buyerId", buyerId)
                .getResultList();
    }

    @Override
    public List<Order> findByProduct_Id(Long productId) {
        String jpql = "SELECT o FROM Order o WHERE o.product.id = :productId";
        return em.createQuery(jpql, Order.class)
                .setParameter("productId", productId)
                .getResultList();
    }
}

