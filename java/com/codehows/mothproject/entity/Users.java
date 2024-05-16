package com.codehows.mothproject.entity;

import com.codehows.mothproject.constant.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Users {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role", nullable = false)
    private UserRole user_role;

    @Column(name = "kakao")
    private String kakao;

    @CreatedDate
    @Column(name = "regdate")
    private LocalDateTime regdate;

    @Column(name = "user_condition", nullable = false)
    private String user_condition;
}
