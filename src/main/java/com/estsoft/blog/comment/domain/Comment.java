package com.estsoft.blog.comment.domain;

import com.estsoft.blog.article.domain.Article;
import com.estsoft.blog.comment.dto.CommentResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String body;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Comment(Article article, String body) {
        this.article = article;
        this.body = body;
    }

    public CommentResponse toDTO() {
        return new CommentResponse(id, article.getId(), body, createdAt, article.toDTO());
    }

    public CommentResponse toDTOWithoutArticle() {
        return new CommentResponse(id, article.getId(), body, createdAt, null);
    }

    public void update(String body) {
        this.body = body;
    }
}
