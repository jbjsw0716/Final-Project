package com.keduit.board.entity;

import com.keduit.board.constant.Admin;
import com.keduit.board.dto.MemberFormDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", unique = true)
    private String email;   //이메일

    @Column(name = "password")
    private String password; //비밀번호


    @Column(name = "nickname", unique = true)
    private String nickname;    //닉네임 / 사용자 아이디

    //
    @Column(name = "join_date")
    private LocalDate joinDate = LocalDate.now(); //가입일

    @Column(name = "admin", nullable = false)
    private Admin Role;  //권한(사용자, 관리자)

    @Column(name = "status", nullable = false)
    private boolean status;  //회원의 활성화된 상태 여부(회원 탈퇴 관련)

    //보류
//    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
//    private List<UserActivity> activities;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<RequestMovie> requestMovies;

    public static Member createMember(MemberFormDTO memberFormDTO, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setNickname(memberFormDTO.getNickname());
        member.setEmail(memberFormDTO.getEmail());
        String password = passwordEncoder.encode(memberFormDTO.getPassword());
        member.setPassword(password);
        member.setRole(Admin.USER);
        return member;

    }

    public void updatePwd(String newPwd, PasswordEncoder passwordEncoder) {
        String newpassword = passwordEncoder.encode(newPwd);
        this.password = newpassword;
    }

    public void  findMyEmail(String email){
        this.email = email;
    }


}
