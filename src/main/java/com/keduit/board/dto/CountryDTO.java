package com.keduit.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
@Getter
@Setter
@ToString
public class CountryDTO {

    private Long countryId; //국가 아이디

    private String countryName; //국가명
}
