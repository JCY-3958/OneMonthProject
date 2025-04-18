package com.codehows.mothproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Uploadfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fno", nullable = false)
    private Long fno;

    @ManyToOne
    @JoinColumn(name = "bno", nullable = false)
    private Board board;

    @Column(name = "original_file_name", nullable = false)
    private String original_file_name;

    @Column(name = "saved_file_name", nullable = false)
    private String saved_file_name;
}
