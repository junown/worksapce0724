package com.kh.server.repository;

import com.kh.server.entity.Member;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    
    Optional<Member> findById(Long id);
    
    void delete(Member member);
    
    boolean existsByUserId(String userId);
    
    Optional<Member> findByUserId(String userId);
}