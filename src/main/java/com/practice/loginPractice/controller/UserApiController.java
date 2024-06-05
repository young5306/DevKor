package com.practice.loginPractice.controller;

import com.practice.loginPractice.dto.AddUserRequest;
import com.practice.loginPractice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request){
        userService.save(request); //회원가입 메서드 호출
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 로그아웃을 담당하는 핸들러 SecurityContextLogoutHandler의 logout() 메서드 호출
        new SecurityContextLogoutHandler().logout(request, response, // 서블릿 요청과 응답 객체를 나타냄. 이를 통해 로그아웃 후에 적절한 응답을 생성하고, 필요에 따라 리디렉션을 수행
                SecurityContextHolder.getContext().getAuthentication()); // 현재 사용자의 인증 객체
        return "redirect:/login";
    }
}
