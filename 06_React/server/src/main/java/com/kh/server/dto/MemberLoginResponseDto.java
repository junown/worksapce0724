package com.kh.server.dto;

import com.kh.server.entity.Member;
import lombok.Data;

@Data
public class MemberLoginResponseDto {
    private Long id;
    private String userId;
    private String name;
    private String email;


    public MemberLoginResponseDto(Member member) {
        this.id = member.getId();
        this.userId = member.getUserId();
        this.name = member.getName();
        this.email = member.getEmail();
    }
}