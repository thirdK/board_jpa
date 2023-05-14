package com.example.springdatajpa02.service;

import com.example.springdatajpa02.domain.Board;
import com.example.springdatajpa02.domain.BoardRepository;
import com.example.springdatajpa02.domain.User;
import com.example.springdatajpa02.domain.UserRepository;
import com.example.springdatajpa02.dto.BoardRequestDto;
import com.example.springdatajpa02.dto.BoardResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class BoardServiceTest {
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private BoardService boardService;
    private BoardRequestDto boardRequestDto;
    @BeforeEach
    void setUp(){
        boardRequestDto = new BoardRequestDto();
        boardRequestDto.setTitle("test title");
        boardRequestDto.setContent("test content");
        boardRequestDto.setUserId(1L);
    }

    @Test
    @DisplayName("저장")
    void save(){
        User user = new User();
        Board board = boardRequestDto.toEntity(user);

        when(boardRepository.save(any(Board.class))).thenReturn(board);
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

        //회원 번호 설정이 불가능함
        assertThat(boardService.save(boardRequestDto, 1L)).isEqualTo(null);
    }

    @Test
    @DisplayName("저장 예외 발생")
    void saveException(){
        User user = new User();
        Board board = boardRequestDto.toEntity(user);

//        when(boardRepository.save(any(Board.class))).thenReturn(board); 불필요한 스텁
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());


        assertThatThrownBy(() -> boardService.save(boardRequestDto, 1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 회원 번호");
    }

    @Test
    @DisplayName("전체 조회")
    void findAll(){
        User user = new User();
        Sort sort = Sort.by(Sort.Direction.DESC, "registerDate");
        when(boardRepository.findAll(sort)).thenReturn(List.of(boardRequestDto.toEntity(user)));
        assertThat(boardService.findAll().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("수정")
    void update(){
        User user = new User();
        when(boardRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(boardRequestDto.toEntity(user)));

        assertThat(boardService.update(1L, boardRequestDto)).isEqualTo(1L);
    }

}














