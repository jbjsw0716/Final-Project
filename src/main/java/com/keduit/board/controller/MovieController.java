
package com.keduit.board.controller;

import com.keduit.board.dto.ArticleDTO;
import com.keduit.board.dto.MovieDTO;

import com.keduit.board.entity.Movie;
import com.keduit.board.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public String MovieList(Model model) {
        List<MovieDTO> movielist = movieService.getAllMovies();
   //     List<MovieDTO> recentMovies = movieService.findRecentMoviesWithDetails();//20개 가져오기..?
        System.out.println("무비컨트롤러====="+ movielist);
        model.addAttribute("movies", movielist);
     //   model.addAttribute("recentMovies", recentMovies);
        return "movie-list";
}




    //영화상세페이지
    @GetMapping("/movies/{movieId}")
    public String ArticleDetails(@PathVariable Long movieId, Model model) {
        MovieDTO amovie = movieService.getMovieDetailsByMovieId(movieId);
        model.addAttribute("movieDTO", amovie);
        return "moviedetail";
}}


