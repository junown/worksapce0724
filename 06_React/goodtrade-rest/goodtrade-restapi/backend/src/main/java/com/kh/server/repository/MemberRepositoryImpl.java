package com.kh.server.repository;

import com.kh.server.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Member save(Member member) {
        if (member.getId() == null) {
            // 새 엔티티인 경우 INSERT
            em.persist(member);
        } else {
            // 기존 엔티티인 경우 UPDATE
            member = em.merge(member);
        }
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    @Transactional
    public void delete(Member member) {
        if (em.contains(member)) {
            // 영속성 컨텍스트에 있는 경우
            em.remove(member);
        } else {
            // 영속성 컨텍스트에 없는 경우
            em.remove(em.merge(member));
        }
    }

    @Override
    public boolean existsByUserId(String userId) {
        String jpql = "SELECT COUNT(m) FROM Member m WHERE m.userId = :userId";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("userId", userId)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        String jpql = "SELECT m FROM Member m WHERE m.userId = :userId";
        try {
            Member member = em.createQuery(jpql, Member.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
            return Optional.of(member);
        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty();
        }
    }
}

