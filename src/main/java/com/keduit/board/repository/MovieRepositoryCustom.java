package com.keduit.board.repository;

import com.keduit.board.entity.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MovieRepositoryCustom {
    List<Movie> findRecentMovies();
}
