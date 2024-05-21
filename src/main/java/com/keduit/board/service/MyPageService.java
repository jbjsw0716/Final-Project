package com.keduit.board.service;

import com.keduit.board.dto.MemberDTO;
import com.keduit.board.dto.UserFindRequestDTO;

import com.keduit.board.entity.Member;

import com.keduit.board.entity.RequestMovie;
import com.keduit.board.entity.Review;
import com.keduit.board.repository.FindMyIdRepository;
import com.keduit.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static com.keduit.board.entity.QMember.member;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final FindMyIdRepository findMyIdRepository;

    //Member에 구현한 List<Review>를 userId로 가져오기 (리뷰가져오기)
    public List<Review> getReviewsByUserId(Long userId) {
        Member reviews = memberRepository.findById(userId).orElse(null);
        if (reviews != null) {
            return reviews.getReviews();
        }
        return Collections.emptyList(); // 사용자가 작성한 리뷰가 없는 경우 빈 리스트 반환
    }

    public List<RequestMovie> getRequestMovieByUserId(Long userId) {
        Member movies = memberRepository.findById(userId).orElse(null);
        if (movies != null) {
            return movies.getRequestMovies();
        }
        return Collections.emptyList(); // 사용자가 작성한 리뷰가 없는 경우 빈 리스트 반환
    }


//userId로 내정보가져오기
public Optional<Member> getMemberById(Long userId) {
    return memberRepository.findById(userId);
    }

    //비밀번호 찾기 수정
//    public Long updatePassword(String email,String newPwd) {
//        System.out.println("이멜넘어가요" +email);
//        Member member = memberRepository.findByEmail(email);
//        //이메일로 회원찾기
//        if (member == null) {
//            throw new EntityNotFoundException("등록된 회원이 없습니다.");
//        } //없으면 NFE
//        member.updatePassword(newPwd, passwordEncoder);
//        //해당회원이있을시 새로운 비밀번호랑 암호와된 비밀번호를 수정
//        memberRepository.save(member); //변경된 비밀번호 저장후 고유번호 반환
//        return member.getUserId();
//    }

    //아이디 찾기
    public Map<String, String> findUserId(String email) {
        Optional<Member> member = findMyIdRepository.findByEmail(email);
        if (!member.isPresent()) {
            throw new CustomException("해당 아이디를 찾을 수 없습니다.");
        }else {
        }

        Map<String, String> result = new HashMap<>();
        result.put("email", member.get().getEmail());
        return result;
    }}
