package com.keduit.board.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
//@Transactional
public class ParsingServiceTest {
    @Autowired
    ParsingService parsingService;

    @Test
    @DisplayName("파싱 테스트")
    public void saveMovieTests(){
        parsingService.saveMovie();
    }
}