package com.keduit.board.entity;

import com.keduit.board.entity.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long genreId;   //장르 아이디

    @Column(name = "genre_name", unique = true)
    private String genreName;   //장르명

    //한 개의 장르에 여러 개의 영화가 매핑될 수 있음 -> 일대다 관계
    @OneToMany(mappedBy = "genreId", fetch = FetchType.LAZY)
    private List<Movie> movies;
}


