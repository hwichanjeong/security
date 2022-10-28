package com.example.security.web;

import com.example.security.domain.members.Member;
import com.example.security.domain.members.MemberRepository;
import com.example.security.web.dto.MemberFormDto;
import com.example.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {
//    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberrepository;

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        System.out.println("memberForm");
        return "member/MemberForm";
    }

    @PostMapping(value = "new")
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "member/MemberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/MemberForm";
        }

        return "redirect:/";
    }
    @GetMapping(value = "/login")
    public String loginMember(){
        return "member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/memberLoginForm";
    }
    @GetMapping(value = "/My")
    public String MyPage(Model model){

        return "member/MyPage";
    }
    @GetMapping("/MyEmail")
    public String MyEmail(){
        return "member/MyEmail";
    }
}
