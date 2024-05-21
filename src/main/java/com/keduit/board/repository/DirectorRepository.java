package com.keduit.board.repository;

import com.keduit.board.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
//    Director findByName(String directorName);
    Director findByMovieTitle(String movieTitle);
//
//    Director findByMovieId(Long movieId);
}