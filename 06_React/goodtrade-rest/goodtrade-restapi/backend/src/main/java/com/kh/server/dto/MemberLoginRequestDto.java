package com.kh.server.dto;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
    private String id;
    private String pwd;
}