package com.keduit.board.dto;


import com.keduit.board.entity.Review;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ReviewDTO {
        private Long reviewId;
        private Long userId;
        private Long movieId;
        private Double reviewScore;
        private String reviewTitle;
        private String reviewContent;
        private Timestamp createdDate;

        // 생성자, 게터, 세터 등 필요한 메소드를 추가할 수 있습니다.
//
//        private static ModelMapper modelMapper = new ModelMapper();
//        //  review -> ReviewDTO
//        public static ReviewDTO of(Review review){
//                return modelMapper.map(review, ReviewDTO.class);
//        }
    }
