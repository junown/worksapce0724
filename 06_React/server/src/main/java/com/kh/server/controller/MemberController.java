package com.kh.server.controller;

import com.kh.server.dto.MemberLoginRequestDto;
import com.kh.server.dto.MemberLoginResponseDto;
import com.kh.server.dto.MemberSignupRequestDto;
import com.kh.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody MemberSignupRequestDto dto) {
        try {
            memberService.signup(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginRequestDto dto) {
        try {
            MemberLoginResponseDto responseDto = memberService.login(dto);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}