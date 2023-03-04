package com.example.commerce.member;

import com.example.commerce.member.dto.MemberDto;
import com.example.commerce.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckUsernameValidator extends AbstractValidator<MemberDto>{
    private final MemberRepository memberRepository;


    @Override
    protected void doValidate(MemberDto dto, Errors errors) {
        if (memberRepository.existsById(dto.toEntity().getUserId())) {
            errors.rejectValue("userId", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
        }
    }
}
