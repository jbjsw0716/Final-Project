package com.keduit.board.dto;

import com.keduit.board.entity.*;
import lombok.*;
import org.modelmapper.ModelMapper;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class MovieDTO {
    private Long movieId;
    private String title;               // 제목
    private String runningTime;         // 러닝타임
    private String releaseDate;         // 개봉일
    private String ratingName;          // 관람 등급
    private String actor;               // 배우
    private double score;               // 평점

    //장르 엔티티
    private String genreName;           // 장르명
    //국가 엔티티
    private String countryName;         // 국가명
    // 감독 엔티티
    private String name;               // 감독명
    // 부가 정보 엔티티
    private String posterImage;         // 영화 포스터 이미지
    private String synopsis;            // 시놉시스

    //ModelMapper 라이브러리를 사용하여 DTO 객체와 엔티티 객체 간의 매핑을 수행 -> DTO와 엔티티 객체 간의 변환을 쉽게 처리하기 위함
    private static ModelMapper modelMapper = new ModelMapper();

    public static MovieDTO of(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }
}