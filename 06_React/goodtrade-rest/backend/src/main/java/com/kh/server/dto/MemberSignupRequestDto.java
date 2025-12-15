package com.kh.server.dto;

import com.kh.server.entity.Member;
import lombok.Data;

@Data
public class MemberSignupRequestDto {
    // 1. 리액트에서 보내는 이름(key)과 똑같이 맞춰줍니다.
    private String id;
    private String pwd;
    private String name;
    private String address;
    private int age;

    public Member toEntity() {
        return Member.builder()
                .userId(id)
                .password(pwd)
                .name(name)
                 .address(address)
                 .age(age)
                .build();
    }
}