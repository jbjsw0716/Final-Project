package com.keduit.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
@Getter
@Setter
@ToString
public class GenreDTO {
    private Long genreId;   //장르 아이디

    private String genreName;   //장르명
}
