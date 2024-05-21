package com.keduit.board.dto;

import com.keduit.board.constant.RequestStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class RequestMovieDTO {

    private Long requestMovieId;
    private Long userId;
    private Timestamp createdDate;
    private String requestMovieTitle;
    private RequestStatus requestStatus;
}
