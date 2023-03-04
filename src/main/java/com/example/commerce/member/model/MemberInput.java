package com.example.commerce.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MemberInput {
    private String userId;
    private String password;
    private String userName;
    private String birth;
    private String phone;

}
