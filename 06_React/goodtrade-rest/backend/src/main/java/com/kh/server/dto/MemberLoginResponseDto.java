package com.kh.server.dto;

import com.kh.server.entity.Member;
import lombok.Data;

@Data
public class MemberLoginResponseDto {
    private Long id;
    private String userId;
    private String name;
    private String address;
    private int age;

    public MemberLoginResponseDto(Member member) {
        this.id = member.getId();
        this.userId = member.getUserId();
        this.name = member.getName();
        this.address = member.getAddress();
        this.age = member.getAge();
    }
}