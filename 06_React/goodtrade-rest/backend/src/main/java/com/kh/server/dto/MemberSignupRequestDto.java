package com.kh.server.dto;

import com.kh.server.entity.Member;
import lombok.Data;

@Data
public class MemberSignupRequestDto {
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