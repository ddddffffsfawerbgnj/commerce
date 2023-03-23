package com.example.commerce.serviece.impl;

import com.example.commerce.component.MailComponents;
import com.example.commerce.entity.Member;
import com.example.commerce.model.MemberInput;
import com.example.commerce.repository.MemberRepository;
import com.example.commerce.serviece.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

    /* 회원가입 */
    @Override
    public boolean register(MemberInput parameter) {
        Optional<Member> optionalMember =
                memberRepository.findById(parameter.getUserId());

        if (optionalMember.isPresent()) {

            return false;
        }

        String encPassword = BCrypt.hashpw(parameter.getPassword(),
                BCrypt.gensalt());

        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .userId(parameter.getUserId())
                .password(encPassword)
                .userName(parameter.getUserName())
                .birth(parameter.getBirth())
                .phone(parameter.getPhone())
                .subscription(LocalDateTime.now())
                .emailAuthYn(false)
                .emailAuthKey(uuid)
                .build();
        memberRepository.save(member);

        String email = parameter.getUserId();
        String subject = "commerce에 가입되었습니다.";
        String text = "<p>commerce에 가입을되었습니다.<p><p>아래 링크를 클릭하셔서 가입을 완료해주세요" +
                ".</p><div><a target='_blank' " +
                "href='http://localhost:8081/member/email-auth?id=" + uuid +
                "'> 가입 완료 링크 </a></div>";
        mailComponents.sendMail(email, subject, text);

        return true;
    }

    @Override
    public boolean emailAuth(String uuid) {
        Optional<Member> optionalMember =
                memberRepository.findByEmailAuthKey(uuid);

        if (!optionalMember.isPresent()) {
            return false;
        }

        Member member = optionalMember.get();

        if (member.isEmailAuthYn()) {
            return false;
        }

        member.setEmailAuthYn(true);
        memberRepository.save(member);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findById(username);
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        if (!member.isEmailAuthYn()) {
            throw new InternalAuthenticationServiceException("계정 활성화 이후에 다시 " +
                    "로그인 해주세요.");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (member.isAdminYn()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(member.getUserId(), member.getPassword(), grantedAuthorities);
    }

}
