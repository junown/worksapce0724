package com.kh.server.dto;

import com.kh.server.entity.Member;
import lombok.Data;

@Data
public class MemberSignupRequestDto {
    private String userId;
    private String password;
    private String name;
    private String email;

    public Member toEntity() {
        return Member.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .email(email)
                .build();
    }
}