package com.keduit.board.controller;

import com.keduit.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberStatusController {


        @Autowired
        private MemberService memberService;

        @PatchMapping("/deactivate")
        public ResponseEntity<String> withdrawMember(@RequestParam String email) {
            memberService.deactivateMember(email);
            return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
        }
    }

