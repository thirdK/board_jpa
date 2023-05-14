package com.example.springdatajpa02.controller;

import com.example.springdatajpa02.dto.BoardRequestDto;
import com.example.springdatajpa02.dto.BoardResponseDto;
import com.example.springdatajpa02.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/boards/v1/*")
@RequiredArgsConstructor
public class BoardRestController {
    private final BoardService boardService;

    @GetMapping("/list")
    public List<BoardResponseDto> findList(){
        return boardService.findAll();
    }

    @PostMapping("/board")
    public Long save(@RequestBody final BoardRequestDto boardRequestDto, HttpServletRequest req){
//        Long userId = (Long)req.getSession().getAttribute("userId");
        Long userId = 1L; //임시 회원
        boardRequestDto.setUserId(userId);
        return boardService.save(boardRequestDto, userId);
    }

    @PatchMapping("/{boardId}")
    public Long modify(@PathVariable Long boardId, @RequestBody final BoardRequestDto boardRequestDto){
        return boardService.update(boardId, boardRequestDto);
    }
}
