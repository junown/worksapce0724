package com.kh.server.controller;

import com.kh.server.dto.MemberDeleteRequestDto;
import com.kh.server.dto.MemberLoginRequestDto;
import com.kh.server.dto.MemberLoginResponseDto;
import com.kh.server.dto.MemberSignupRequestDto;
import com.kh.server.dto.MemberUpdateRequestDto;
import com.kh.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody MemberSignupRequestDto dto) {
        System.out.println(" 회원가입 요청 옴: " + dto.getId());
        try {
            memberService.signup(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto dto) {
        System.out.println(" 사용자 정보 수정 요청 옴: " + id);
        try {
            MemberLoginResponseDto responseDto = memberService.update(id, dto);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestBody MemberDeleteRequestDto dto) {
        System.out.println(" 사용자 삭제 요청 옴: " + id);
        try {
            memberService.delete(id, dto);
            return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}