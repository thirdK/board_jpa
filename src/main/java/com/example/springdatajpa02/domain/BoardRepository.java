package com.example.springdatajpa02.domain;

import com.example.springdatajpa02.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

//엔티티와 같은 패키지에 위치해야 함
public interface BoardRepository extends JpaRepository<Board, Long> {
}
