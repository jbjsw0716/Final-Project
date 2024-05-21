package com.keduit.board.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "articles")
@Getter
@Setter
@ToString
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleid;

    @Column(name = "a_heading", length = 255)
    private String articleHeading;

    @Column(name = "a_subheading", length = 255)
    private String articleSubHeading;

    @Column(name = "a_image_url", length = 255)
    private String articleImageUrl;

    @Column(name = "a_content", columnDefinition = "TEXT")
    private String articleContent;



}
