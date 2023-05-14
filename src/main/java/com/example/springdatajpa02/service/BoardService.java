package com.example.springdatajpa02.service;

import com.example.springdatajpa02.domain.Board;
import com.example.springdatajpa02.domain.BoardRepository;
import com.example.springdatajpa02.domain.User;
import com.example.springdatajpa02.domain.UserRepository;
import com.example.springdatajpa02.dto.BoardRequestDto;
import com.example.springdatajpa02.dto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    /**
     * 신규 게시물 저장
     * @param boardRequestDto 게시물 정보
     * @param userId 회원 번호
     * @return 게시물 번호
     * @throws IllegalArgumentException 잘못된 회원 번호
     */
    @Transactional
    public Long save(BoardRequestDto boardRequestDto, Long userId) throws IllegalArgumentException{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 회원 번호"));
        Board board = boardRepository.save(boardRequestDto.toEntity(user));

        return board.getId();
    }

    /**
     * 전체 게시물 조회
     * 페이징 X
     * 정렬 O
     * @return 조회 결과
     */
    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC,"registerDate");
        List<Board> list = boardRepository.findAll(sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }


    /**
     * 게시물 수정
     * @param boardId 게시물 번호
     * @param boardRequestDto 게시물 수정 정보 (title, content)
     * @return 게시물 번호
     * @throws IllegalArgumentException 잘못된 게시물 번호
     */
    @Transactional
    public Long update(Long boardId, BoardRequestDto boardRequestDto) throws IllegalArgumentException{
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("잘못된 게시물 번호"));
        board.update(boardRequestDto.getTitle(), boardRequestDto.getContent());
        return boardId;
    }
}
