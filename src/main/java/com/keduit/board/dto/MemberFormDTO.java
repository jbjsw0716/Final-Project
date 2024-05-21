package com.keduit.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@ToString
public class MemberFormDTO {

    @NotBlank(message = "닉네임은 필수 입력입니다.")
    private String nickname;

    @NotEmpty(message = "이메일은 필수 입력입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력입니다.")
    @Length(min = 8, message = "비밀번호는 8자 이상 입력해주세요.")
    private String password;


    private Date joinDate = new Date();

}
