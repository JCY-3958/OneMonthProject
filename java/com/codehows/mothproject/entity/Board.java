package com.codehows.mothproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bno", updatable = false)
    private Long bno;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "hashtag")
    private String hashtag;

    @Column(name = "hitcount")
    private Long hitcount;

    @Column(name = "heart")
    private Long heart;

    @CreatedDate
    @Column(name = "regdate")
    private LocalDateTime regdate;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "board_condition", nullable = false)
    private String board_condition;

    public void deleteBoard() {
        this.board_condition = "Y";
    }

    public void increaseHeart() {
        this.heart += 1;
    }

    public void decreaseHeart() {
        this.heart -= 1;
    }
}
