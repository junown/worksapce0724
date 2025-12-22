package com.kh.server.service;

import com.kh.server.dto.MemberDeleteRequestDto;
import com.kh.server.dto.MemberLoginRequestDto;
import com.kh.server.dto.MemberLoginResponseDto;
import com.kh.server.dto.MemberSignupRequestDto;
import com.kh.server.dto.MemberUpdateRequestDto;

public interface MemberService {
    Long signup(MemberSignupRequestDto dto);
    MemberLoginResponseDto login(MemberLoginRequestDto dto);
    MemberLoginResponseDto update(Long id, MemberUpdateRequestDto dto);
    void delete(Long id, MemberDeleteRequestDto dto);
}