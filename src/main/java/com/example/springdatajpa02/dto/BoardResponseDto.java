package com.example.springdatajpa02.dto;

import com.example.springdatajpa02.domain.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private Long userId;
    private String userLoginId;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.registerDate = entity.getRegisterDate();
        this.updateDate = entity.getUpdateDate();
        this.userId = entity.getUser().getId();
        this.userLoginId = entity.getUser().getLoginId();
    }
}
