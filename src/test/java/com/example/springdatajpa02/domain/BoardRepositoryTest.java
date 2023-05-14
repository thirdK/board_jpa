package com.example.springdatajpa02.domain;

import com.example.springdatajpa02.embedded.Address;
import com.example.springdatajpa02.enumType.UserGender;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    private User user;
    private Board board;

    @BeforeEach
    void setUp(){
        user = User.builder()
                .loginId("aaa")
                .password("1234")
                .address(new Address("강남구", "103호"))
                .gender(UserGender.M)
                .name("김철수")
                .build();
        board = Board.builder()
                .title("test title")
                .content("test content")
                .user(user)
                .build();
    }

    @Test
    @DisplayName("저장, 조회")
    void save(){


        boardRepository.save(board);

        Board entity = boardRepository.findById(1L).get();

        assertThat(entity.getTitle()).isEqualTo(board.getTitle());
        assertThat(entity.getContent()).isEqualTo(board.getContent());
        assertThat(entity.getUser().getName()).isEqualTo(board.getUser().getName());
    }

    @Test
    @DisplayName("전체 조회")
    void findAll(){
        boardRepository.save(board);
        Long count = boardRepository.count();
        List<Board> boards = boardRepository.findAll();

        assertThat(count).isEqualTo(boards.size());
    }

    @Test
    @DisplayName("삭제💣")
    void delete(){
        boardRepository.save(board);
        Board entity = boardRepository.findById(board.getId()).orElseThrow(IllegalStateException::new);

        boardRepository.delete(entity);
    }
}