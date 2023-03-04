package com.example.commerce.member.controller;

import com.example.commerce.member.CheckUsernameValidator;
import com.example.commerce.member.dto.MemberDto;
import com.example.commerce.member.model.MemberInput;
import com.example.commerce.member.repository.MemberRepository;
import com.example.commerce.member.serviece.MemberService;
import com.example.commerce.member.serviece.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Log4j2
public class MemberController {

    private final MemberRepository memberRepository;

    private final MemberService memberService;
    private final MemberServiceImpl memberServiceImpl;

    private final CheckUsernameValidator checkUsernameValidator;

    /*커스텀 유효성 검증*/
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUsernameValidator);
    }

    /*로그인*/
    @RequestMapping("/member/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false)
                        String exception, Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "member/login";
    }

    /*회원가입*/
    @GetMapping("/member/register")
    public String register() {

        return "member/register";
    }
    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request,
                                 MemberInput parameter) {

        boolean result = memberService.register(parameter);

        model.addAttribute("result", result);


        return "member/register_success";
    }
    @PostMapping("/member/registerProc")
    public String joinProc(@Valid MemberDto memberDto, Errors errors,
                           Model model) {

        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("memberDto", memberDto);

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = memberServiceImpl.validateHandling(errors);


            /* 회원가입 페이지로 다시 리턴 */
            return "/member/register";
        }

        return "redirect:/member/login";
    }

    /*계정 활성화*/
    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {
        String uuid = request.getParameter("id");

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

        return "member/email_auth";
    }
}
