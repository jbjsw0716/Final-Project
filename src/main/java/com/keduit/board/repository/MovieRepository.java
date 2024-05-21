package com.keduit.board.repository;

import com.keduit.board.dto.MovieDTO;
import com.keduit.board.entity.Genre;
import com.keduit.board.entity.Movie;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Primary
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryCustom {
    //기본 메소드들과 사용자 정의 메소드가 같이 선언
    List<Movie> findByGenreId(Genre genre);
    Movie findByTitle(String title);

    Movie findByMovieId(Long movie);



}
