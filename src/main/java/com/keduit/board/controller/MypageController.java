package com.keduit.board.controller;


import com.keduit.board.dto.MemberFormDTO;

import com.keduit.board.entity.Member;
import com.keduit.board.entity.RequestMovie;
import com.keduit.board.entity.Review;
import com.keduit.board.repository.MemberRepository;
import com.keduit.board.service.MemberService;
import com.keduit.board.service.MyPageService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MypageController {

    private final MyPageService myPageService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

//principal : 현재 인증된 사용자
    @GetMapping("/mypage")
    public String mypage(Model model, Principal principal) {

        String username = principal.getName();
        Member member = memberRepository.findByEmail(username);
        Long userId = member.getUserId();

        List<Review> reviews = myPageService.getReviewsByUserId(userId);
        List<RequestMovie> movies = myPageService.getRequestMovieByUserId(userId);
        model.addAttribute("reviews", reviews);
        model.addAttribute("movies", movies);
        return "myPage/mypage";
    }

    @GetMapping("/myinfo")
    public String myinfo(Model model, Principal principal) {
        //principal : 현재 접속해있는 사용자의 정보를 불러옴 (우리는 Email을 불러옴).
        //Principal을 이용하여 userId를 가져오는 메소드
            try{
                Member members = memberService.getMemberDtl((principal.getName()));
                model.addAttribute("members", members);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 회원입니다.");
            model.addAttribute("member", new MemberFormDTO());
            return "member/loginForm";
            //memberFormDTO 에서 가져오는 객체들을 member의 변수명으로 호출함
        }
        return "myPage/myinfo";
    }

    //비밀번호변경 페이지이동
    @GetMapping("/updatePass")
    public String passUpdateForm(Member member, Model model) {
        model.addAttribute(member);
        return "myPage/password";
    }


    @PostMapping("/checkPwd") // (4)
    public String updatePassword(@ModelAttribute("member") Member member, Errors errors, Model model, RedirectAttributes attributes) {
        // 이메일을 사용하여 회원을 찾습니다.
        String email = member.getEmail();
        Member storedMember = memberRepository.findByEmail(email);

        if (storedMember == null) {
            attributes.addFlashAttribute("error", "해당 이메일로 등록된 회원이 없습니다.");
            return "redirect:/"; // 회원이 없는 경우 메인 페이지로 리다이렉트
        }

        // 현재 비밀번호가 올바른지 확인
        String currentPassword = member.getPassword();
        if (!passwordEncoder.matches(currentPassword, storedMember.getPassword())) {
            attributes.addFlashAttribute("error", "현재 비밀번호가 올바르지 않습니다.");
            return "members/login"; // 비밀번호가 올바르지 않은 경우 비밀번호 변경 페이지로 리다이렉트
        }

        // 새로운 비밀번호로 업데이트
        String newPassword = member.getPassword();
        if (newPassword != null && !newPassword.equals(currentPassword)) {
            // 새로운 비밀번호로 업데이트
            storedMember.updatePwd(newPassword, passwordEncoder);
            memberRepository.save(storedMember);

            // 비밀번호 변경 성공 메시지 설정
            attributes.addFlashAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
        } else {
            attributes.addFlashAttribute("error", "새로운 비밀번호가 현재 비밀번호와 동일합니다.");
        }

        return "redirect:/members/login";
    }



//아이디찾고 값 비교해주기
    @PostMapping(value = "/findIdSuccess")
    public Map<String, String> findId(@RequestBody String email) {
        Map<String, String> id = myPageService.findUserId(email);
        System.out.println("id를 받았을걸????");
        return id;
}
    @GetMapping("/findId")
    public String findIdPage() {
        return "member/findId"; // 아이디 찾기 페이지의 HTML 파일명을 반환합니다.
    }

    @PostMapping("/findId")
    public String findId(@RequestParam("email") String email, Model model) {
        Map<String, String> foundId = myPageService.findUserId(email);

        if (foundId != null) {
            // 아이디를 찾았을 경우, 이메일과 아이디를 모델에 담아서 뷰에 전달
            model.addAttribute("msg", "아이디가 존재합니다.");
            return "member/findId";
        } else {
            // 아이디를 찾지 못했을 경우, 회원가입 폼으로 리다이렉트
            return "redirect:/member/memberForm";
        }
    }}


//관리자모드

//@GetMapping("/admin/item/{itemId}")
//public String itemDtl(@PathVariable("itemId") Long itemId, Model model){
//    try {
//        ItemFormDTO itemFormDTO = itemService.getItemDtl(itemId);
//        model.addAttribute("itemFormDTO", itemFormDTO);
//
//    } catch (EntityNotFoundException e){
//        model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
//        model.addAttribute("itemFromDTO", new ItemFormDTO());
//        return "item/itemForm";
//    }
//    return "item/itemForm";
//}

    //postmapping으로 받을때 <form action="/경로" method="post">input들 넣어주고</form>
    //main으로 돌아가기..








