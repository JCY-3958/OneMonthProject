package com.codehows.mothproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bno", nullable = false)
    private Board board;

    @CreatedDate
    @Column(name = "delete_time")
    private LocalDateTime delete_time;

    @Column(name = "recover_time")
    private LocalDateTime recover_time;

}
