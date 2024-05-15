package com.practice.loginPractice.repository;

import com.practice.loginPractice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // JPA - 쿼리 메서드 규칙에 따라 자동으로 쿼리 생성
    Optional<User> findByEmail(String email); // email로 사용자 정보 가져옴.
}