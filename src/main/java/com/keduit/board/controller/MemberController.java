package com.keduit.board.controller;

import com.keduit.board.dto.MemberFormDTO;
import com.keduit.board.entity.Member;
import com.keduit.board.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDTO",new MemberFormDTO());
        return "member/memberForm";
    }

/*    @PostMapping("/new")
    public String memberForm(MemberFormDTO memberFormDTO){
        Member member = Member.createMember(memberFormDTO, passwordEncoder);
        memberService.saveMember(member);
        return "redirect:/";
    }*/

    //    회원가입 오류 일 경우
    @PostMapping("/new")
    public String newMember(@Valid MemberFormDTO memberFormDTO, BindingResult bindingResult, Model model){
        System.out.println("왔나?");
        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }
        try {
            Member member = Member.createMember(memberFormDTO, passwordEncoder);
            System.out.println("오는건가?");
            memberService.saveMember(member);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }


    @GetMapping("/login")
    public String loginMember(){
        return "member/loginForm";
    }


    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 혹은 비밀번호를 확인하세요.");
        return "/member/loginForm";
    }




}
