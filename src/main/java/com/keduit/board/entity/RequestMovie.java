package com.keduit.board.entity;

import com.keduit.board.constant.RequestStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@Table(name = "request_movie")
public class RequestMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_movie_id")
    private Long requestMovieId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member userId;  //사용자 아이디

    @Column(name = "created_date")
    private Timestamp createdDate;  //작성일

    @Column(name = "request_movie_title")
    private String requestMovieTitle;

    @Column(name = "request_status")
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
}
