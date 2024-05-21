package com.keduit.board.controller;


import com.keduit.board.entity.Member;
import com.keduit.board.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testUpdatePassword_Success() throws Exception {
        String email = "user3@user3.com";
        String currentPassword = "$2a$10$cCIg93zEuz3wbvB1vt2xH.JeMlO.Dp6zgtx.x5DBnc0svO2s4TO0u";
        String newPassword = "newPassword";

        String encodedCurrentPassword = passwordEncoder.encode(currentPassword);
        String encodedNewPassword = passwordEncoder.encode(newPassword);

        Member member = new Member();
        member.setEmail(email);
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/checkPwd")
                        .param("email", email)
                        .param("currentPwd", encodedCurrentPassword)
                        .param("newPwd", encodedNewPassword))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("myPage/password"))
                .andExpect(MockMvcResultMatchers.flash().attributeExists("message"));
    }
    }

