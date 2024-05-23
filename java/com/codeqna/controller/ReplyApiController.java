package com.codeqna.controller;


import com.codeqna.dto.ParentReplyDto;
import com.codeqna.dto.request.ArticleCommentRequest;
import com.codeqna.dto.security.BoardPrincipal;
import com.codeqna.entity.Users;
import com.codeqna.repository.UserRepository;
import com.codeqna.service.ArticleCommentService;
import com.codeqna.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {

    private final ArticleCommentService articleCommentService;
    private final UserRepository userRepository;
    // 3. 댓글 수정


    // 2. 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<ArticleCommentRequest> create(@PathVariable Long articleId,
                                             @RequestBody ArticleCommentRequest articleCommentRequest,
                                                        @AuthenticationPrincipal BoardPrincipal principal) {
        String email  = principal.getName();
        Users user = userRepository.findByEmail(email).orElseThrow();
        articleCommentService.saveArticleComment(articleCommentRequest,email);

        return ResponseEntity.ok()
                .build();
    }




    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<ArticleCommentRequest> update(@PathVariable Long id,
                                                        @RequestBody ArticleCommentRequest articleCommentRequest,
                                                        @AuthenticationPrincipal BoardPrincipal principal) {
        String email  = principal.getName();
        Users user = userRepository.findByEmail(email).orElseThrow();

        articleCommentService.updateArticleComment(id,articleCommentRequest,email );
        // 결과 응답
        return ResponseEntity.ok()
                .build();
    }

    // 4. 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<ArticleCommentRequest> delete(@PathVariable Long id,
                                                        @AuthenticationPrincipal BoardPrincipal principal) {
        String email = principal.getName();
        // 서비스에 위임
        articleCommentService.deleteArticleComment(id,email);
        // 결과 응답
        return ResponseEntity.ok()
                .build();
    }

}
