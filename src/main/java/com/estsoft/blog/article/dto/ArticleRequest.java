package com.estsoft.blog.article.dto;

import com.estsoft.blog.article.domain.Article;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleRequest {
    private String title;

    @JsonAlias({"body", "content"})
    private String content;

    public Article toArticle() {
        return new Article(title, content);
    }
}
