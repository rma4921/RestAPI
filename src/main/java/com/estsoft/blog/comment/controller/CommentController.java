package com.estsoft.blog.comment.controller;

import com.estsoft.blog.article.dto.ArticleResponse;
import com.estsoft.blog.comment.domain.Comment;
import com.estsoft.blog.comment.dto.CommentRequest;
import com.estsoft.blog.comment.dto.CommentResponse;
import com.estsoft.blog.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 정보 생성 REST API
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentResponse> saveComment(@PathVariable(name = "articleId") Long articleId,
                                                       @RequestBody CommentRequest request) {
        Comment response = commentService.saveComment(articleId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.toDTO());
    }
    // 댓글 정보 조회 REST API
    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable(name = "commentId") Long commentId) {
        Comment response = commentService.findCommentById(commentId);
        return ResponseEntity.ok(response.toDTO());
    }

    // 댓글 정보 수정 REST API
    @PutMapping("api/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable(name = "commentId") long id,
                                                         @RequestBody CommentRequest request) {
        Comment comment = commentService.updateArticleById(request, id);
        return ResponseEntity.ok(comment.toDTO());
    }

    // 댓글 정보 삭제 REST API
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable(name = "commentId") long id) {
        commentService.deleteCommentById(id);
        return ResponseEntity.ok().build();
    }

    // 게시글과 댓글 정보를 한번에 조회하는 REST API <-- Article 부분에 작성

}
