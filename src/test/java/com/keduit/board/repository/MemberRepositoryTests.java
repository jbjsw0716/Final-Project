//package com.keduit.board.repository;
//
//import com.keduit.board.entity.Member;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.jdbc.Sql;
//
//import javax.transaction.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//
//@SpringBootTest
//@RequiredArgsConstructor
//public class MemberRepositoryTests {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
////    @Autowired
////    private PasswordEncoder passwordEncoder;
//
//
//    @Test
//    public void testUpdatePasswordByEmail() {
//        // Given
//        String email = "user4@user4.com";
//        String oldPassword = "12345678";
//        String newPassword = "123456788";
//
//        // 새로운 회원 생성
//        Member member = new Member();
//        member.setUserId(3L);
//        member.(true);
//        member.setEmail(email);
//        member.setJoinDate(LocalDate.now());
//        member.setNickname("테스트중");
//        member.setPassword(oldPassword);
//      //  member.setPassword(passwordEncoder.encode(oldPassword)); // 비밀번호를 해시화합니다.
//        member.setStatus(true);
//        memberRepository.save(member);
//
//        //    (admin, email, join_date, nickname, password, status)
//        // When
//        // 존재하는 회원의 이메일과 기존 비밀번호를 사용하여 비밀번호를 변경합니다.
//       // memberRepository.updatePasswordByEmail(email, passwordEncoder.encode(newPassword)); // 이 부분도 수정하여 새 비밀번호를 해시화합니다.
//        memberRepository.updatePasswordByEmail(email, newPassword);
//        // Then
//// 이메일로 회원 조회
//        Member updatedMember = memberRepository.findByEmail(email);
//    }}
//
