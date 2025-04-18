package com.codeqna.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // Auditing 기능을 사용하려면 추가해야 함
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "regdate")
    private LocalDateTime regdate;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "board_condition", nullable = false)
    private String board_condition;

    @Builder
    public Board(String title, String content, String hashtag, Long hitcount, Long heart, String nickname, String board_condition){
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.hitcount = hitcount;
        this.heart = heart;
        this.nickname = nickname;
        this.board_condition = board_condition;
    }

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
