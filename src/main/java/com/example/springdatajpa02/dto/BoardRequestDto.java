package com.example.springdatajpa02.dto;

import com.example.springdatajpa02.domain.Board;
import com.example.springdatajpa02.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class BoardRequestDto {
    private String title;
    private String content;
    private Long userId;

    public Board toEntity(User user){
        return Board.builder()
                .title(title)
                .content(content)
                .hits(0)
                .registerDate(LocalDateTime.now())
                .user(user)
                .build();
    }

}
