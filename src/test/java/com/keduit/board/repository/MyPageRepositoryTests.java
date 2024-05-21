package com.keduit.board.repository;

import com.keduit.board.entity.Member;
import com.keduit.board.entity.Review;

import com.keduit.board.service.MyPageService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class MyPageRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MyPageService requestService;

    @Test
    public void testGetReviewsByUserId() {
        // Mock 데이터 설정
        Long userId = 1L;
        Member member = new Member();
        Review review = new Review();
        review.setReviewId(1L);
        // 필요한 다른 리뷰 속성 설정...

        member.setUserId(userId);
//        member.setReviews(Collections.singletonList(review));

//        // Mock Repository 메서드 호출에 대한 행동 설정
//        when(memberRepository.findById(userId)).thenReturn(Optional.of(member));

        // 서비스 메서드 호출
        List<Review> actualReviews = requestService.getReviewsByUserId(userId);

        // 예상되는 결과와 실제 결과 비교
        assertEquals(1, actualReviews.size());
        assertEquals(review.getReviewId(), actualReviews.get(0).getReviewId());
        // 다른 리뷰 속성에 대한 추가 비교...
    }
}