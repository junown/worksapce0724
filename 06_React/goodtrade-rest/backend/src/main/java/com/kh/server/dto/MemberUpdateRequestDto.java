package com.kh.server.dto;

import lombok.Data;

@Data
public class MemberUpdateRequestDto {
    private String pwd;
    private String name;
    private String address;
    private int age;
}

