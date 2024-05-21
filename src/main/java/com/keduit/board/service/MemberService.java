package com.keduit.board.service;


import com.keduit.board.dto.MemberDTO;
import com.keduit.board.entity.Member;
import com.keduit.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member saveMember(Member member) { /* (MemberFormDTO memberFormDTO) 로 변경 해줘야함 */
        validateDuplicateMember(member); // 중복체크
        System.out.println("여기도 갔다.");
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findmember = memberRepository.findByEmail(member.getEmail());
        if (findmember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

//      시큐리티 없을 때
//    private Member convertToMember(MemberFormDTO memberFormDTO) {
//
//        Member member = new Member();
//        member.setEmail(memberFormDTO.getEmail());
//        member.setPwd(memberFormDTO.getPwd());
//        member.setUsername(memberFormDTO.getUsername());
//        System.out.println("가지고 왔다");
//        return member;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles("user") //admin추가하면 추가
                .build();

    }


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
//        Member member = memberRepository.findByEmail(email);
//
//        if(member == null){
//            throw new UsernameNotFoundException(email);
//        }
//        return User.builder()
//                .username(member.getEmail())
//                .password(member.getPwd())
//                .build();
//    }


    //userId로 내정보 뿌리기
    public Member getMemberDtl(String email) {
        return memberRepository.findByEmail(email);
    }

    //비밀번호 수정
    public void PwdUpdate(String email, String currentPwd, String newPwd) { //매개변수로 수정된 값이 들어오는 변수
        // 이메일을 사용하여 회원을 찾습니다.
        Member member = memberRepository.findByEmail(email);

        if(member==null){
        throw new IllegalArgumentException("해당 이메일로 등록된 회원이 없습니다.");}
        // 현재 비밀번호가 올바른지 확인
        if(!passwordEncoder.matches(currentPwd, member.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 올바르지 않습니다.");}

            // 현재 비밀번호와 새로운 비밀번호가 다를 경우에 수정
            if (!newPwd.equals(currentPwd)) {
                // 회원의 비밀번호를 새로운 암호화된 비밀번호로 수정.
                // updatePwd()암호화된 비밀번호를 저장하라고구현
                member.updatePwd(newPwd,passwordEncoder);

                // 회원 정보를 저장.
                memberRepository.save(member);

                // 회원 비밀번호 수정이 성공했음을 로딩.
                System.out.println("회원 비밀번호 수정 성공");
            }
        }

 //회원탈퇴
    public void deactivateMember(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member != null) {
            member.setStatus(true); // 회원 상태를 비활성화로 변경
            memberRepository.save(member);
        }

    }}

