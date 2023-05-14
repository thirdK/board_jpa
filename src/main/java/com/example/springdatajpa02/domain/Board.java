package com.example.springdatajpa02.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "TBL_BOARD")
public class Board {
    @Id @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 1000, nullable = false)
    private String content;
    private int hits;
    private LocalDateTime registerDate = LocalDateTime.now();
    private LocalDateTime updateDate;
    @ManyToOne(fetch = FetchType.EAGER) // 디폴트가 EAGER라 생략가능
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Builder
    public Board(String title, String content, int hits, LocalDateTime registerDate, LocalDateTime updateDate, User user) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.user = user;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
