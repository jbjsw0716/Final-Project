package com.keduit.board.controller;

import com.keduit.board.dto.ArticleDTO;

import com.keduit.board.dto.GenreDTO;
import com.keduit.board.dto.MovieDTO;

import com.keduit.board.entity.Article;
import com.keduit.board.repository.ArticleRepository;
import com.keduit.board.service.ArticleService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping("/articles")
    public String Articlelist(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "article-list";
    }

    @GetMapping("/articles/{articleId}")
    public String ArticleDetails(@PathVariable Long articleId, Model model) {
        Article articles = articleService.getArticle(articleId);
        ArticleDTO articleDTO = new ArticleDTO(articles);
        model.addAttribute("articles", articleService.getAllArticles());
        model.addAttribute("articleDTO", articleDTO);

        return "articledetail";

    }
}



