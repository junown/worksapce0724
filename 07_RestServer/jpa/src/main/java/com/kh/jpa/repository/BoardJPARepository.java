package com.kh.jpa.repository;

import com.kh.jpa.entity.Board;
import com.kh.jpa.entity.Member;
import com.kh.jpa.enums.CommonEnums;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardJPARepository extends JpaRepository<Board, Long> {
    //상태값을 통한 게시글 조회
    Page<Board> findByStatus(CommonEnums.Status status, Pageable pageable);

    //작성자로 게시글 조회
    List<Board> findByMember(Member member);
    //작성자의 아이디로 조회
    List<Board> findByMemberUserId(String userId);
    
    //제목 또는 내용으로 게시글 검색
    List<Board> findByBoardTitleorContent(String content, String title);
    //조회수가 높은 순으로 게시글 조회
    //특정 작성자의 활성 게시글 조회(페이징)
}
