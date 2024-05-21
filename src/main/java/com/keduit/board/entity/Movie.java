package com.keduit.board.entity;

import com.keduit.board.dto.AdditionalInfoDTO;
import com.keduit.board.dto.CountryDTO;
import com.keduit.board.dto.GenreDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;   //영화 아이디

    @Column(name = "movie_code", unique = true)
    private String movieCode;   // 영화 코드

    @Column(name = "title", nullable = false)
    private String title;   //제목

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genreId;  //장르 아이디

    @Column(name = "running_time")
    private String runningTime;    //러닝타임

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country countryId;  //국가아이디

    @Column(name = "release_date")
    private String releaseDate; //개봉일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private Director directorId;    //감독 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "additional_info_id")
    private AdditionalInfo additionalInfoId;    //부가정보 아이디

    @Column(name = "rating_name")
    private String ratingName;  //관람 등급

    @Column(name = "actor")
    private String actor;   //배우

    @Column(name = "score", precision = 3, scale = 1)
    private double score; // 평점

    @OneToMany(mappedBy = "movieId", fetch = FetchType.LAZY)
    private List<Review> reviews;


}
