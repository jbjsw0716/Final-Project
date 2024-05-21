package com.keduit.board.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request,response);
        if(savedRequest != null){
            response.sendRedirect(savedRequest.getRedirectUrl());
        } else {
            response.sendRedirect("/default-url"); // 저장된 요청이 없는 경우 기본 url로 리디렉션
        }
    }
}