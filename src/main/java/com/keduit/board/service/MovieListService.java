//package com.keduit.board.service;
//
//import com.keduit.board.dto.MovieDTO;
//import com.keduit.board.entity.*;
//import com.keduit.board.repository.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class MovieListService {
//    private final MovieRepository movieRepository;
//    private final GenreRepository genreRepository;
//
//    // 모든 영화 리스트 가져오기
////    public List<MovieDTO> getAllMovies() {
////        List<Movie> movies = movieRepository.findAll();
////        List<MovieDTO> movieDTOs = new ArrayList<>();
////
////        for (Movie movie : movies) {
////            MovieDTO movieDTO = new MovieDTO();
////
////            movieDTO.setTitle(movie.getTitle());                                    // 영화 제목
////            movieDTO.setScore(movie.getScore());                                    // 평점
////            movieDTO.setPosterImage(movie.getAdditionalInfoId().getPosterImage());  // 포스터 이미지
////            movieDTO.setSynopsis(movie.getAdditionalInfoId().getSynopsis());        // 시놉시스
////
////            movieDTOs.add(movieDTO);
////        }
////
////        return movieDTOs;
////    }
//
//    // 특정 장르별 영화 리스트 가져오기
//    public List<MovieDTO> getMoviesByGenreNames(String genreName) {
//        // 장르명 리스트로 해당 장르들을 데이터베이스에서 찾음
////        List<Genre> genres = new ArrayList<>();
//        Genre genre = genreRepository.findByGenreName(genreName);
//        System.out.println("=======> " + genre);
////            genres.add(genre);
//
////            if (genre != null) { // 장르가 존재하는 경우에만 추가
////                genres.add(genre);
////            }
//
//
//        // 해당 장르들에 속하는 모든 영화 리스트를 데이터베이스에서 가져옴
//        List<Movie> movies = new ArrayList<>();
////        for (Genre genre : genres) {
////            movies.addAll(movieRepository.findByGenreId(genre));
////        }
//        movies.addAll(movieRepository.findByGenreId(genre));
//        System.out.println("==================> movies: " + movies);
//
//        // MovieDTO 리스트 초기화
//        List<MovieDTO> movieDTOs = new ArrayList<>();
//
//        // 각 영화에 대해 MovieDTO를 생성하여 리스트에 추가
//        for (Movie movie : movies) {
//            MovieDTO movieDTO = new MovieDTO();
//
//            // 영화 정보 설정
//            movieDTO.setTitle(movie.getTitle());                                    // 영화 제목
//            movieDTO.setScore(movie.getScore());                                    // 평점
//            movieDTO.setPosterImage(movie.getAdditionalInfoId().getPosterImage());  // 포스터 이미지
//            movieDTO.setSynopsis(movie.getAdditionalInfoId().getSynopsis());        // 시놉시스
//            movieDTO.setGenreName(movie.getGenreId().getGenreName());                  // 장르명
//
//            // MovieDTO 리스트에 추가
//            movieDTOs.add(movieDTO);
//        }
//
//        // MovieDTO 리스트 반환
//        return movieDTOs;
//    }
//
//    // 해당 영화 제목의 상세 정보 가져오기
//    public List<MovieDTO> getMovieDetailsByTitle(String movieTitle) {
//        Movie movie = movieRepository.findByTitle(movieTitle);
//        // 해당하는 영화 제목에 해당하는 영화가 없는 경우 예외 처리나 로깅을 추가할 수 있습니다. 여기서는 빈 리스트를 반환하도록 처리합니다.
//        if (movie == null) {
//            return Collections.emptyList();
//        }
//
//        List<MovieDTO> movieDTOs = new ArrayList<>();
//
//        MovieDTO movieDTO = new MovieDTO();
//
//        movieDTO.setTitle(movie.getTitle());                                    // 영화 제목
//        movieDTO.setRunningTime(movie.getRunningTime());                        // 러닝타임
//        movieDTO.setReleaseDate(movie.getReleaseDate());                        // 개봉일
//        movieDTO.setRatingName(movie.getRatingName());                          // 관람 등급
//        movieDTO.setActor(movie.getActor());                                    // 배우
//        movieDTO.setScore(movie.getScore());                                    // 평점
//        movieDTO.setGenreName(movie.getGenreId().getGenreName());               // 장르명
//        movieDTO.setCountryName(movie.getCountryId().getCountryName());         // 국가명
//        movieDTO.setName(movie.getDirectorId().getName());                      // 감독명
//        movieDTO.setPosterImage(movie.getAdditionalInfoId().getPosterImage());  // 포스터 이미지
//        movieDTO.setSynopsis(movie.getAdditionalInfoId().getSynopsis());        // 시놉시스
//
//        movieDTOs.add(movieDTO);
//
//        return movieDTOs;
//    }
//}
