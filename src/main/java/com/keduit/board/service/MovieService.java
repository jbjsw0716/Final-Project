package com.keduit.board.service;


import com.keduit.board.dto.AdditionalInfoDTO;

import com.keduit.board.dto.MovieDTO;
import com.keduit.board.entity.*;

import com.keduit.board.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieRepositoryCustom movieRepositoryCustom;


    //리스트 형태로 전부 가져오기
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieList = new ArrayList<>();
//        Movie movie = new Movie();
        for( Movie movie : movies){

            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setMovieId(movie.getMovieId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setRunningTime(movie.getRunningTime());
            movieDTO.setReleaseDate(movie.getReleaseDate());
            movieDTO.setRatingName(movie.getRatingName());
            movieDTO.setActor(movie.getActor());
            movieDTO.setScore(movie.getScore());
            movieDTO.setGenreName(movie.getGenreId().getGenreName()); //Genreid로 genreName가져오기
            movieDTO.setCountryName(movie.getCountryId().getCountryName());
            movieDTO.setName(movie.getDirectorId().getName());
            movieDTO.setPosterImage(movie.getAdditionalInfoId().getPosterImage());
            movieDTO.setSynopsis(movie.getAdditionalInfoId().getSynopsis());

            movieList.add(movieDTO);

        }

        return movieList;
    }


    //List 형태로 movieId로 영화 정보가져오기
    public MovieDTO getMovieDetailsByMovieId(Long movieId) {
        MovieDTO movieDTO = new MovieDTO();
        Movie movies = movieRepository.findByMovieId(movieId);
        movieDTO.setTitle(movies.getTitle());
        movieDTO.setRunningTime(movies.getRunningTime());
        movieDTO.setReleaseDate(movies.getReleaseDate());
        movieDTO.setRatingName(movies.getRatingName());
        movieDTO.setActor(movies.getActor());
        movieDTO.setScore(movies.getScore());
        movieDTO.setGenreName(movies.getGenreId().getGenreName());
        movieDTO.setCountryName(movies.getCountryId().getCountryName());
        movieDTO.setName(movies.getDirectorId().getName()); //감독정보
        movieDTO.setPosterImage(movies.getAdditionalInfoId().getPosterImage());
        movieDTO.setSynopsis(movies.getAdditionalInfoId().getSynopsis());

        return movieDTO;
        }


        //최신영화 20개만 뽑아오기
        public List<MovieDTO> findRecentMoviesWithDetails() {
            List<Movie> recentMoviesList = movieRepositoryCustom.findRecentMovies();//20개뽑아올 메소드 호출
            List<MovieDTO> recentMovieDTOList = new ArrayList<>();

            //1. Movie 타입의 영화20개를 담은 리스트를 for문 돌려서 MovieDTO로(mapper) 변환해서 arrayList객체에 담아줌
            for (Movie recentMovies : recentMoviesList){
                MovieDTO movieDTO = MovieDTO.of(recentMovies); //Movie타입을 DTO 객체로 변환
                recentMovieDTOList.add(movieDTO);
            }
            return recentMovieDTOList;


    }
}
