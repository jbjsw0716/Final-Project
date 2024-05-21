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
@Table(name = "director")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id")
    private Long directorId;    //감독 아이디

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;  //영화 제목

    @Column(name = "name")
    private String name;    //감독 이름

    @OneToMany(mappedBy = "directorId", fetch = FetchType.LAZY)
    private List<Movie> directors;  //감독이 감독한 영화들을 저장하는 컬렉션
}
