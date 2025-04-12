package com.estsoft.blog.article.domain;

import com.estsoft.blog.article.dto.ArticleResponse;
import com.estsoft.blog.comment.dto.CommentResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse toDTO() {
        return new ArticleResponse(id, title, content, createdAt, updatedAt);
    }

    public ArticleResponse toDTO(List<CommentResponse> comments) {
        return new ArticleResponse(id, title, content, createdAt, updatedAt, comments);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
