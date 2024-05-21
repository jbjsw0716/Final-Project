package com.keduit.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@ToString
public class CommentDTO {

        private Long commentId;
        private Long userId;
        private Long movieId;
        private String commentContent;
        private Timestamp createdDate;

        // 생성자, 게터, 세터 등 필요한 메소드를 추가할 수 있습니다.
    }

