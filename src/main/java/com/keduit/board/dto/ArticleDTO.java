package com.keduit.board.dto;

import com.keduit.board.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArticleDTO {
    private Long articleid;

    private String articleHeading;

    private String articleSubHeading;

    private String articleImageUrl;

    private String articleContent;


    public ArticleDTO(Article article) {
        this.articleid = article.getArticleid();
        this.articleHeading = article.getArticleSubHeading();
        this.articleImageUrl = article.getArticleImageUrl();
        this.articleContent = article.getArticleContent();
    }
}
