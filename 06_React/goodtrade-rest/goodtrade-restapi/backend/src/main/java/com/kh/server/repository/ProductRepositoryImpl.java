package com.kh.server.repository;

import com.kh.server.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Product save(Product product) {
        if (product.getId() == null) {
            // 새 엔티티인 경우 INSERT
            em.persist(product);
        } else {
            // 기존 엔티티인 경우 UPDATE
            product = em.merge(product);
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        String jpql = "SELECT p FROM Product p JOIN FETCH p.seller";
        return em.createQuery(jpql, Product.class).getResultList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        String jpql = "SELECT p FROM Product p JOIN FETCH p.seller WHERE p.id = :id";
        try {
            Product product = em.createQuery(jpql, Product.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(product);
        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void delete(Product product) {
        if (em.contains(product)) {
            em.remove(product);
        } else {
            em.remove(em.merge(product));
        }
    }

    @Override
    public List<Product> findByCategory(String category) {
        String jpql = "SELECT p FROM Product p JOIN FETCH p.seller WHERE p.category = :category";
        return em.createQuery(jpql, Product.class)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public List<Product> findByStatus(String status) {
        String jpql = "SELECT p FROM Product p JOIN FETCH p.seller WHERE p.status = :status";
        return em.createQuery(jpql, Product.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<Product> findByCategoryAndStatus(String category, String status) {
        String jpql = "SELECT p FROM Product p JOIN FETCH p.seller WHERE p.category = :category AND p.status = :status";
        return em.createQuery(jpql, Product.class)
                .setParameter("category", category)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<Product> findBySeller_Id(Long sellerId) {
        String jpql = "SELECT p FROM Product p JOIN FETCH p.seller WHERE p.seller.id = :sellerId";
        return em.createQuery(jpql, Product.class)
                .setParameter("sellerId", sellerId)
                .getResultList();
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        String jpql = "SELECT p FROM Product p JOIN FETCH p.seller WHERE p.name LIKE :name";
        return em.createQuery(jpql, Product.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
}

