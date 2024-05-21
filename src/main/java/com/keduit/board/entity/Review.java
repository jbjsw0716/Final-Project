package com.keduit.board.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;  //리뷰 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member userId;  //사용자 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movieId;  //영화 아이디

    @Column(name = "review_score", precision = 3, scale = 1)
    private Double reviewScore; //리뷰 점수

    @Column(name = "review_title")
    private String reviewTitle; //리뷰 제목

    @Column(name = "review_content")
    private String reviewContent;   //리뷰 내용

    @Column(name = "created_date")
    private Timestamp createdDate;  //작성일
}
