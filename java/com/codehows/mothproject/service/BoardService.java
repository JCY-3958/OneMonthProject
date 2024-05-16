package com.codehows.mothproject.service;

import com.codehows.mothproject.entity.Board;
import com.codehows.mothproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository repository;

    //게시물 상세 페이지 가져오기
    public Board findByBno(Long bno) {
        return repository.findByBno(bno);
    }

    //게시물 삭제
    @Transactional //이게 왜 붙어야하는지 모르겠음
    public void deleteBoard(Long bno) {
        System.out.println("요까지2");
        Board board = repository.findByBno(bno);
        board.deleteBoard();
    }

}
