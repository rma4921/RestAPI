package com.estsoft.blog.article.service;

import com.estsoft.blog.article.domain.Article;
import com.estsoft.blog.article.dto.ArticleRequest;
import com.estsoft.blog.article.dto.ArticleResponse;
import com.estsoft.blog.article.repository.BlogRepository;
import com.estsoft.blog.comment.dto.CommentResponse;
import com.estsoft.blog.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final CommentService commentService;

    // 추가
    public Article saveArticle(ArticleRequest request) {
        return blogRepository.save(request.toArticle());
    }

    // 전부 조회
    public List<Article> findAllArticles() {
        return blogRepository.findAll();
    }

    // 단 건 조회
    public Article findArticleById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "에 해당하는 게시글이 없습니다."));
    }

    // 수정
    public Article updateArticleById(ArticleRequest request, Long id) {
        Article article = findArticleById(id);
        article.update(request.getTitle(), request.getContent());
        return blogRepository.save(article);
    }

    // 단 건 삭제
    public void deleteArticleById(Long id) {
        blogRepository.deleteById(id);
    }

    // 전부 삭제
    public void deleteAllArticles() {
        blogRepository.deleteAll();
    }

    // 게시글과 댓글 정보를 한번에 조회하는 REST API
    public ArticleResponse getArticlesAndComments(Long articleId) {
        Article article = findArticleById(articleId);

        List<CommentResponse> CommentResponses = commentService.findCommentsByArticleId(articleId);
        return article.toDTO(CommentResponses);
    }
}
