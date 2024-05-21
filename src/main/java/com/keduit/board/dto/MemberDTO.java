package com.keduit.board.dto;


import com.keduit.board.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString
public class MemberDTO {

        private Long userId;
        private String email;
        private String password;
        private String nickname;
        private Timestamp joinDate;
        private boolean admin;
        private boolean active;
        private List<ReviewDTO> reviews;
        private List<CommentDTO> comments;
        private List<RequestMovieDTO> requestMovies;

        // 생성자, 게터, 세터 등 필요한 메소드를 추가할 수 있습니다.

        private static ModelMapper modelMapper = new ModelMapper();




        public static MemberDTO fromMember(Member member) {
                MemberDTO myinfo = new MemberDTO();
                myinfo.setUserId(member.getUserId());
                myinfo.setEmail(member.getEmail());
                myinfo.setNickname(member.getNickname());
                // 나머지 필드 설정...
                return myinfo;
    }

    //Member -> memberDTO로 변경해 쓰려고 가져옴
        // Member 객체의 필드 값을 MemberDTO 객체의 필드로 매핑하는 역할
    public static MemberDTO of(Member member){
            return modelMapper.map(member, MemberDTO.class);
    }

}

