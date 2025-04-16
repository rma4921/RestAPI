package com.estsoft.blog.comment.dto;

import com.estsoft.blog.article.dto.ArticleResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponse {
    private Long commentId;
    private Long articleId;
    private String body;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm::ss") // 날짜 fomat 지정
    private LocalDateTime createdAt;
    private ArticleResponse article;
}