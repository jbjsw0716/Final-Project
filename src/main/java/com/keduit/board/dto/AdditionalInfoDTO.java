package com.keduit.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.List;
@Getter
@Setter
@ToString
public class AdditionalInfoDTO {
    private Long additionalInfoId;  //부가정보 아이디
    private String movieCode;   // 영화 코드
    private String posterImage; //포스터 이미지

    private String synopsis; //시놉시스

    }
