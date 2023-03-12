package com.example.commerce.serviece;

import com.example.commerce.model.MemberInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    //회원가입
    boolean register(MemberInput parameter);

    //uuid에 해당하는 계정 활성화
    boolean emailAuth(String uuid);

}
