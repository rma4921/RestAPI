package com.estsoft.blog.external.dto;

import com.estsoft.blog.article.domain.Article;
import com.estsoft.blog.comment.domain.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestExternal {

    @JsonProperty("postId")
    private Long articleId;

    private String body;

    public Comment toComment(Article article) {
        return new Comment(article, body);
    }
}