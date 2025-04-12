package com.estsoft.blog.article.controller;

import com.estsoft.blog.article.domain.Article;
import com.estsoft.blog.article.dto.ArticleRequest;
import com.estsoft.blog.article.dto.ArticleResponse;
import com.estsoft.blog.article.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // 추가
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> saveArticle(@RequestBody ArticleRequest request) {
        Article response = blogService.saveArticle(request);

        // Article -> ArticleResponse로 변환 후 리턴
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.toDTO());
    }

    // 전부 조회
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        List<Article> allArticles = blogService.findAllArticles();
        List<ArticleResponse> response = allArticles.stream()
                .map(Article::toDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    // 단 건 조회
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable long id) {
        Article article = blogService.findArticleById(id);
        return ResponseEntity.ok(article.toDTO());
    }

    // 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable long id, @RequestBody ArticleRequest request) {
        Article article = blogService.updateArticleById(request, id);
        return ResponseEntity.ok(article.toDTO());
    }

    // 단 건 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable long id) {
        blogService.deleteArticleById(id);
        return ResponseEntity.noContent().build(); // 204 상태 코드
    }

    // 전부 삭제
    @DeleteMapping("/api/articles")
    public ResponseEntity<ArticleResponse> deleteAllArticles() {
        blogService.deleteAllArticles();
        return ResponseEntity.noContent().build(); // 204 상태 코드
    }

    // 게시글과 댓글 정보를 한번에 조회하는 REST API
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<ArticleResponse> getArticleWithComments(@PathVariable(name = "articleId") Long articleId) {
        ArticleResponse response = blogService.getArticlesAndComments(articleId);
        return ResponseEntity.ok(response);
    }
}
