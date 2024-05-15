package com.practice.loginPractice.repository;

import com.practice.loginPractice.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
