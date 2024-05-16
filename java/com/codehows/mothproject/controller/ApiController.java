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
    @PutMapping("/api/{bno}")
    public ResponseEntity<Void> delete(@PathVariable Long bno) {
        boardService.deleteBoard(bno);
        System.out.println("요까지1");

        return ResponseEntity.ok().build();
    }
}
