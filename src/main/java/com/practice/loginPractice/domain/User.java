package com.practice.loginPractice.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="users")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Builder
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    /*
    User 클래스가 상속한 UserDetails 클래스는 스프링 시큐리티에서 사용자의 인증 정보를 담아두는 인터페이스.
    스프링 시큐리티에서 해당 객체를 통해 인증 정보를 가져오므로 필수 오버라이드 메서드가 있다.
    -> 7가지

     */
    // 1) 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user")); // 사용자가 가지고 있는 권한의 목록을 반환
    }

    // 2) 사용자의 id 반환(고유 값)
    @Override
    public String getUsername() {
        return email; // unique 속성이 적용된 이메일 반환
    }

    // 3) 사용자의 password 반환
    @Override
    public String getPassword() {
        return password; // 비밀번호를 암호화해서 저장해야
    }

    // 4) 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 5) 계정 잠근 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금되었는지 확인하는 로직
        return true; // true -> 잠금되지 않았음
    }

    // 6) 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 7) 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직
        return true; // true -> 사용 가능
    }
}
