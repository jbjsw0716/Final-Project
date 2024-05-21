package com.keduit.board.service;


import com.keduit.board.entity.Article;
import com.keduit.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    //리스트 형태로 전부 가져오기
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }



    public Article getArticle(Long articleId) {
        return articleRepository.findById(articleId).get();
    }
    }

