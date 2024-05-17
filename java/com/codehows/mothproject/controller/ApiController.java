package com.codehows.mothproject.controller;

import com.codehows.mothproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final BoardService boardService;

    //게시물 삭제
    @PutMapping("/api/delete/{bno}")
    public ResponseEntity<Void> delete(@PathVariable Long bno) {
        boardService.deleteBoard(bno);
        System.out.println("요까지1");

        return ResponseEntity.ok().build();
    }

    //좋아요를 눌렀을 경우
    @PutMapping("/api/heart/{bno}/like")
    public ResponseEntity<Void> increaseHeart(@PathVariable Long bno) {
        boardService.increaseHeart(bno);
        return ResponseEntity.ok().build();
    }

    //좋아요를 취소했을 경우
    @PutMapping("/api/heart/{bno}/unlike")
    public ResponseEntity<Void> decreaseHeart(@PathVariable Long bno) {
        boardService.decreaseHeart(bno);
        return ResponseEntity.ok().build();
    }
}
