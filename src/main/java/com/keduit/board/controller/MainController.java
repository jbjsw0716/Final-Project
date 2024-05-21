package com.keduit.board.controller;

import com.keduit.board.dto.MovieDTO;
import com.keduit.board.service.MainService;
import com.keduit.board.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;
    private final MovieService movieService;

    @GetMapping("/")
    public String MovieList(Model model) {
        List<MovieDTO> movielist = mainService.getAllMovies();
        List<MovieDTO> recentMovies = movieService.findRecentMoviesWithDetails();//20개 가져오기..?
        model.addAttribute("movies", movielist);
        model.addAttribute("recentMovies", recentMovies); //20
        return "main";
    }

    @GetMapping("/movies-top20")
    public String showRecentMovies(Model model) {
        List<MovieDTO> recentMovies = movieService.findRecentMoviesWithDetails();
        model.addAttribute("recentMovies", recentMovies);
        return "main"; // 해당 뷰 이름으로 설정해주세요.
    }
}
