package com.example.commerce.member.dto;

import com.example.commerce.member.entity.Member;
import com.example.commerce.member.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSessionDto implements Serializable {
    private String userId;
    private String password;
    private String userName;
    private String birth;
    private String phone;
    private Role role;

    public MemberSessionDto(Member member) {
        this.userId = member.getUserId();
        this.password = member.getPassword();
        this.userName = member.getUserName();
        this.birth = member.getBirth();
        this.phone = member.getPhone();
        this.role = member.getRole();
    }
}
