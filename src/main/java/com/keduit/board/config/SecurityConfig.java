package com.keduit.board.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //객체를 매개변수로 받아서 보안 필터 체인설정

        System.out.println("---------------시큐리티 필터체인한다잉----------");

//폼 기반 로그인 설정. 로그인 페이지, 로그인 성공/실패 후의 URL, 사용자 이름 매개변수, 로그아웃 등을 설정
        http.formLogin()
                .loginPage("/members/login")
                .successHandler(customAuthenticationSuccessHandler())
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                //요청에 대한 인증 및 권한 부여 설정
                .logoutSuccessUrl("/");


        http.authorizeRequests()
                .mvcMatchers("/", "/members/**","/movies/**", "/movies", "/articles/**").permitAll() //url 추가해줘야함
                .mvcMatchers("/admin/**").hasRole("ADMIN") //관리자랑 유저 추가할 경우 url을 추가해줘야됨.
                .requestMatchers(new AntPathRequestMatcher("/lib/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/scss/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/img/**")).permitAll()
                .anyRequest().authenticated(); //위에 설정한 페이지에 인증되지 않은 사용자 접근 금지(안될경우 빼면됨)

        http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        http.csrf().disable();
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler(){
        return new com.keduit.board.config.CustomAuthenticationSuccessHandler();
    }

}
