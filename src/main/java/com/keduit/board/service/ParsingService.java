package com.keduit.board.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.keduit.board.entity.*;
import com.keduit.board.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class ParsingService {
    private final GenreRepository genreRepository;
    private final DirectorRepository directorRepository;
    private final CountryRepository countryRepository;
    private final MovieRepository movieRepository;
    private final AdditionalInfoRepository additionalInfoRepository;
    private final RestTemplate restTemplate;

    @Value("${movie.api.key}")
    private String movieApiKey;

    @Value("${movie.codes}")
    private String[] movieCodes;

    public void saveGenre(){
        for (String code : movieCodes) {
            String apiUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=" + movieApiKey + "&movieCd=" + code;
            JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);

            JsonNode genreNode = response.path("movieInfoResult").path("movieInfo").path("genres");

            if (genreNode.isArray() && genreNode.size() > 0) {
                String genreName = genreNode.get(0).path("genreNm").asText();

                if (genreRepository.findByGenreName(genreName) == null) {
                    Genre genre = new Genre();
                    genre.setGenreName(genreName);
                    genreRepository.save(genre);
                } else {
                    System.out.println("이미 존재하는 장르: " + genreName);
                }
            } else {
                System.out.println("영화에 대한 장르 정보가 없습니다.");
            }
        }
    }

    public void saveDirector() {
        for (String code : movieCodes) {
            String apiUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=" + movieApiKey + "&movieCd=" + code;
            JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);

            JsonNode directorNode = response.path("movieInfoResult").path("movieInfo").path("directors");

            if (directorNode.isArray() && directorNode.size() > 0) {
                // 감독명 추출
                String directorName;
                JsonNode directorNameNode = directorNode.get(0).path("peopleNm");
                if (directorNameNode != null && !directorNameNode.isNull() && !directorNameNode.asText().isEmpty()) {
                    directorName = directorNameNode.asText();
                } else {
                    directorName = "감독명 미제공"; // 감독명이 없는 경우에는 감독명 미제공으로 설정
                }

                // 영화 제목 추출
                String movieTitle = response.path("movieInfoResult").path("movieInfo").path("movieNm").asText();

                // 감독 엔티티 생성 및 설정
                Director director = new Director();
                director.setName(directorName);
                director.setMovieTitle(movieTitle);

                // 감독 엔티티를 데이터베이스에 저장
                directorRepository.save(director);
            } else {
                // 감독 정보가 없는 경우에도 감독명 미제공으로 데이터를 생성하여 저장
                String movieTitle = response.path("movieInfoResult").path("movieInfo").path("movieNm").asText();
                Director director = new Director();
                director.setName("감독명 미제공");
                director.setMovieTitle(movieTitle);
                directorRepository.save(director);
                System.out.println("영화에 대한 감독 정보가 없습니다. 감독명 미제공으로 저장합니다.");
            }
        }
    }


    public void saveCountry(){
        for (String code : movieCodes) {
            String apiUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=" + movieApiKey + "&movieCd=" + code;
            JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);

            JsonNode countryNode = response.path("movieInfoResult").path("movieInfo").path("nations").get(0).path("nationNm");
            String countryName = countryNode.asText();

            // 이미 존재하는 국가인지 확인
            if (countryRepository.findByCountryName(countryName) == null) {
                // 국가 엔티티 생성 및 저장
                Country country = new Country();
                country.setCountryName(countryName);
                countryRepository.save(country);
            } else {
                System.out.println("이미 존재하는 국가: " + countryName);
            }
        }
    }

    public void saveMovie(){
        saveGenre();
        saveCountry();
        saveDirector();

        for (String code : movieCodes) {
            String apiUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=" + movieApiKey + "&movieCd=" + code;
            JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);

            // 제목
            String movieTitle = response.path("movieInfoResult").path("movieInfo").path("movieNm").asText();

            // 러닝타임
            JsonNode showTmNode = response.path("movieInfoResult").path("movieInfo").path("showTm");
            String runningTime;
            if (showTmNode != null && !showTmNode.isNull()) {
                runningTime = String.valueOf(showTmNode.asInt());
            } else {
                // 러닝타임 정보가 없는 경우에는 '러닝타임 미제공'으로 설정
                runningTime = "러닝타임 미제공";
            }

            // 개봉일
            JsonNode openDtNode = response.path("movieInfoResult").path("movieInfo").path("openDt");
            String releaseDate;
            if (openDtNode != null && !openDtNode.isNull()) {
                releaseDate = openDtNode.asText();
            } else {
                releaseDate = "정보 미제공"; // 기본값 설정
            }

            // 관람등급
            JsonNode auditsNode = response.path("movieInfoResult").path("movieInfo").path("audits");
            String ratingName;
            if (auditsNode != null && auditsNode.isArray() && auditsNode.size() > 0) {
                ratingName = auditsNode.get(0).path("watchGradeNm").asText();
            } else {
                ratingName = "정보 미제공";
            }

            // 영화 코드
            String movieCode = response.path("movieInfoResult").path("movieInfo").path("movieCd").asText();

            // 배우
            String actors = "";
            JsonNode actorsNode = response.path("movieInfoResult").path("movieInfo").path("actors");
            if (actorsNode.size() > 0) {
                for (int i = 0; i < Math.min(3, actorsNode.size()); i++) {
                    JsonNode actorNode = actorsNode.get(i);
                    String actorName = actorNode.path("peopleNm").asText();
                    actors += actorName; // 콤마 추가하지 않고 이름만 추가
                    // 마지막 배우 이름이 아닌 경우에만 콤마 추가
                    if (i < Math.min(3, actorsNode.size()) - 1) {
                        actors += ", ";
                    }
                }
            } else {
                actors = "배우 정보 미제공";
            }

            // 감독 엔티티 조인
            String directorMovieTitle = response.path("movieInfoResult").path("movieInfo").path("movieNm").asText();
            Director director = directorRepository.findByMovieTitle(directorMovieTitle);

            // 장르 엔티티 조인
            String genreName = response.path("movieInfoResult").path("movieInfo").path("genres").get(0).path("genreNm").asText();
            if (genreName.equals("성인물(에로)")) {
                System.out.println("성인물(에로) 장르는 제외됩니다.");
                continue;
            }
            Genre genre = genreRepository.findByGenreName(genreName);

            // 국가 엔티티 조인
            String countryName = response.path("movieInfoResult").path("movieInfo").path("nations").get(0).path("nationNm").asText();
            Country country = countryRepository.findByCountryName(countryName);

            // 부가정보 엔티티 조회
            AdditionalInfo additionalInfo = additionalInfoRepository.findByMovieCode(movieCode);

            // 부가정보가 없으면 다음 영화로 넘어감
            if (additionalInfo == null) {
                System.out.println("영화 부가정보를 찾을 수 없습니다. 영화 코드: " + movieCode);
                continue;
            }

            Movie movie = new Movie();
            movie.setTitle(movieTitle);
            movie.setRunningTime(runningTime);
            movie.setReleaseDate(releaseDate);
            movie.setRatingName(ratingName);
            movie.setMovieCode(movieCode);
            movie.setDirectorId(director);
            movie.setCountryId(country);
            movie.setGenreId(genre);
            movie.setActor(actors);
            movie.setAdditionalInfoId(additionalInfo);

            // 영화의 평점을 랜덤으로 생성 (0부터 10까지, 소수점 한자리까지)
            Random random = new Random();
            double score = random.nextInt(101) / 10.0; // 0부터 10까지의 정수를 생성하고 10으로 나누어 소수점 한자리까지 표현
            movie.setScore(score);

            movieRepository.save(movie);
        }
    }
}
