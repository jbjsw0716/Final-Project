package com.keduit.board.service;

import com.keduit.board.dto.MovieDTO;
import com.keduit.board.entity.Movie;
import com.keduit.board.repository.AdditionalInfoRepository;
import com.keduit.board.repository.CountryRepository;
import com.keduit.board.repository.DirectorRepository;
import com.keduit.board.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final MovieRepository movieRepository;
    private final AdditionalInfoRepository additionalInfoRepository;
    private final CountryRepository countryRepository;
    private final DirectorRepository directorRepository;
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
            movieDTO.setScore(movie.getScore());
            movieDTO.setGenreName(movie.getGenreId().getGenreName()); //Genreid로 genreName가져오기
            movieDTO.setCountryName(movie.getCountryId().getCountryName());
            movieDTO.setName(movie.getDirectorId().getName());
            movieDTO.setPosterImage(movie.getAdditionalInfoId().getPosterImage());
            movieDTO.setSynopsis(movie.getAdditionalInfoId().getSynopsis());

            movieList.add(movieDTO);
            System.out.println("====================스코어다" + movie.getScore());
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
        movieDTO.setGenreName(movies.getGenreId().getGenreName()); //이것도 되고 밑에방식도 된다
        movieDTO.setCountryName(movies.getCountryId().getCountryName());
        movieDTO.setName(movies.getDirectorId().getName()); //감독정보
        movieDTO.setPosterImage(movies.getAdditionalInfoId().getPosterImage());
        movieDTO.setSynopsis(movies.getAdditionalInfoId().getSynopsis());

        return movieDTO;
    }
}
