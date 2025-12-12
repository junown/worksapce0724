package com.kh.server.service;

import com.kh.server.dto.MemberLoginRequestDto;
import com.kh.server.dto.MemberLoginResponseDto;
import com.kh.server.dto.MemberSignupRequestDto;
import com.kh.server.entity.Member;
import com.kh.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 만들어줌 (의존성 주입)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long signup(MemberSignupRequestDto dto) {
        if (memberRepository.existsByUserId(dto.getUserId())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        return memberRepository.save(dto.toEntity()).getId();
    }

    // 2. 로그인 로직
    public MemberLoginResponseDto login(MemberLoginRequestDto dto) {
        Member member = memberRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!member.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        return new MemberLoginResponseDto(member);
    }
}