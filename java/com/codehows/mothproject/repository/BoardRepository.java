package com.codehows.mothproject.repository;

import com.codehows.mothproject.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //optional을 써야 서비스에서 orElseThrow 사용 가능
    Board findByBno(Long bno);

    @Transactional
    @Modifying
    @Query("update Board set hitcount = hitcount + 1 where bno = :bno")
    void incrementHitCount(@Param("bno") Long bno);
}
