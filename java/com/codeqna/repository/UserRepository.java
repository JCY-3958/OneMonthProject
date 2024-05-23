package com.codeqna.repository;

import com.codeqna.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByNickname(String nickname);
    Optional<Users> findByEmail(String email);
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    //쿼리모음
    @Query("SELECT u FROM Users u WHERE u.nickname LIKE %:nickname%")
    List<Users> findByNicknameContaining(@Param("nickname") String keyword);
}
