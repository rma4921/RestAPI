package com.estsoft.blog.external.service;

import com.estsoft.blog.article.domain.Article;
import com.estsoft.blog.article.dto.ArticleRequest;
import com.estsoft.blog.article.repository.BlogRepository;
import com.estsoft.blog.comment.domain.Comment;
import com.estsoft.blog.comment.repository.CommentRepository;
import com.estsoft.blog.external.dto.CommentRequestExternal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public void saveArticles() {
        String url = "https://jsonplaceholder.typicode.com/posts";

        ResponseEntity<List<ArticleRequest>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        List<ArticleRequest> articles = response.getBody();
        for (ArticleRequest dto : articles) {
            Article article = dto.toArticle();
            blogRepository.save(article);
        }
    }

    public void saveComments() {
        String url = "https://jsonplaceholder.typicode.com/comments";

        ResponseEntity<List<CommentRequestExternal>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );
        List<CommentRequestExternal> comments = response.getBody();

        for (CommentRequestExternal dto : comments) {
            Long articleId = dto.getArticleId();

            Article article = blogRepository.findById(articleId)
                    .orElseThrow(() -> new IllegalArgumentException(articleId + "에 해당하는 게시글이 없습니다."));

            Comment comment = dto.toComment(article);
            commentRepository.save(comment);
        }
    }
}
