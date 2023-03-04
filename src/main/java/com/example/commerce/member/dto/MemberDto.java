package com.example.commerce.member.dto;

import com.example.commerce.member.entity.Member;
import com.example.commerce.member.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    @NotBlank(message = "아이디(이메일)는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 " +
            "형식이 올바르지 않습니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 8~16자 영문 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "확인 비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 8~16자 영문 소문자, 숫자, 특수문자를 사용하세요.")
    private String rePassword;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String userName;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "1900-00-00" +
            " 형식으로 입력해주세요.")
    private String birth;

    @NotBlank(message = "연락처는 필수 입력 값입니다.")
    @Pattern(regexp = "(01[016789])(\\\\d{3,4})(\\\\d{4})", message = "010-0000-000" +
            " 형식으로 입력해주세요.")
    private String phone;

    public Member toEntity() {
        Member member = Member.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .birth(birth)
                .phone(phone)
                .role(Role.USER)
                .build();
        return member;
    }
}
