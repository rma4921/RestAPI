package com.estsoft.blog.article.dto;

import com.estsoft.blog.comment.dto.CommentResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"articleId", "title", "content", "createdAt", "updatedAt"})
public class ArticleResponse {
    private long ArticleId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> comments;

    public ArticleResponse(Long id, String title, String content,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, title, content, createdAt, updatedAt, null);
    }
}

