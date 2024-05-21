package com.keduit.board.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@Table(name = "additional_info")
public class AdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "additional_info_id")
    private Long additionalInfoId;  //부가정보 아이디

    @Column(name = "movie_code", unique = true)
    private String movieCode;   // 영화 코드

    @Column(name = "poster_image")
    private String posterImage; //포스터 이미지

    @Column(name = "synopsis", columnDefinition = "TEXT") //시놉시스 컬럼 추가
    private String synopsis; //시놉시스

    //일대다 관계 설정, 지연 로딩 방식
    @OneToMany(mappedBy = "additionalInfoId", fetch = FetchType.LAZY)
    private List<Movie> additionalInfo;
}