package com.practice.loginPractice.config;

import com.practice.loginPractice.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userDetailService;

    // 스프링 시큐리티 비활성화
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .requestMatchers("/login", "/signup", "/user") // 특정 요청과 일치하는 url에 대한 액세스를 설정
                .permitAll()// 특정 url에 대해 누구나 접근 가능하도록 설정
                .anyRequest() // 위에서 설정한 url 이외의 요청에 대해 설정  =>
                .authenticated() //별도 인가는 필요하지 않지만 인증이 접근할 수 있도록  => 그 외 모든 요청은 인증된 사용자만 접근 가능
                .and()
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // 로그인 페이지 경로를 설정
                        .defaultSuccessUrl("/form") // 로그인이 완료됐을 때 이동할 경로 설정
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")  // 로그아웃이 완료됐을 때 이동할 경로 설정
                        .invalidateHttpSession(true) // 로그아웃 이후 세션 삭제 여부 설정
                )
                .csrf(AbstractHttpConfigurer::disable) // crsf공격을 비활성화
                .build();
    }
    // 인증 관리자 관련 설정
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // 사용자 정보 서비스 설정
        // 사용자 정보를 가져올 서비스를 설정=> 이 때 설정하는 서비스 클래스는 UDS상속받은 클래스
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // 비밀번호를 암호화하기 위한 인코더 설정
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
