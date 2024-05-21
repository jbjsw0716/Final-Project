package com.keduit.board.repository;

import com.keduit.board.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByGenreName(String genreName);
//    Genre findByMovieId(Long movieId);

}
