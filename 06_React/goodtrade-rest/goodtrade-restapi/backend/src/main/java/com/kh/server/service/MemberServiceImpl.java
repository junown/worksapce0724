package com.kh.server.service;

import com.kh.server.dto.MemberDeleteRequestDto;
import com.kh.server.dto.MemberLoginRequestDto;
import com.kh.server.dto.MemberLoginResponseDto;
import com.kh.server.dto.MemberSignupRequestDto;
import com.kh.server.dto.MemberUpdateRequestDto;
import com.kh.server.entity.Member;
import com.kh.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long signup(MemberSignupRequestDto dto) {
            if (memberRepository.existsByUserId(dto.getId())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        return memberRepository.save(dto.toEntity()).getId();
    }

    @Override
    public MemberLoginResponseDto login(MemberLoginRequestDto dto) {
        Member member = memberRepository.findByUserId(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!member.getPassword().equals(dto.getPwd())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        return new MemberLoginResponseDto(member);
    }

    @Override
    @Transactional
    public MemberLoginResponseDto update(Long id, MemberUpdateRequestDto dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

       
        if (dto.getPwd() != null && !dto.getPwd().isEmpty()) {
            member.setPassword(dto.getPwd());
        }
        
        member.setName(dto.getName());
        member.setAddress(dto.getAddress());
        member.setAge(dto.getAge());

        Member updatedMember = memberRepository.save(member);
        return new MemberLoginResponseDto(updatedMember);
    }

    @Override
    @Transactional
    public void delete(Long id, MemberDeleteRequestDto dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!member.getPassword().equals(dto.getPwd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        memberRepository.delete(member);
    }
}
