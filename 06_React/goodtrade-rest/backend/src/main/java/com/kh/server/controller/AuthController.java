package com.kh.server.controller;

import com.kh.server.dto.MemberLoginRequestDto;
import com.kh.server.dto.MemberLoginResponseDto;
import com.kh.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginRequestDto dto) {
        System.out.println(" 로그인 요청 옴: " + dto.getId());
        try {
            MemberLoginResponseDto responseDto = memberService.login(dto);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}

