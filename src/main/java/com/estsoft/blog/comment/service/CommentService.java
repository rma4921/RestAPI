package com.estsoft.blog.comment.service;

import com.estsoft.blog.article.domain.Article;
import com.estsoft.blog.article.repository.BlogRepository;
import com.estsoft.blog.comment.domain.Comment;
import com.estsoft.blog.comment.dto.CommentRequest;
import com.estsoft.blog.comment.dto.CommentResponse;
import com.estsoft.blog.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    // 댓글 정보 생성 REST API
    public Comment saveComment(Long articleId, CommentRequest request) {
        Article article = blogRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException(articleId + "에 해당하는 게시글이 없습니다."));

        return commentRepository.save(new Comment(article, request.getBody()));
    }

    // 댓글 정보 조회 REST API
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(commentId + "에 해당하는 댓글이 존재하지 않습니다."));
    }

    // 게시글 Id로 댓글 전부 조회
    public List<CommentResponse> findCommentsByArticleId(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        return comments.stream()
                .map(Comment::toDTOWithoutArticle)
                .toList();
    }

    // 댓글 정보 수정 REST API
    public Comment updateArticleById(CommentRequest request, Long id) {
        Comment comment = findCommentById(id);
//        if (comment.getArticle() == null) {
//            throw new IllegalStateException("댓글에 연결된 게시글이 없습니다.");
//        }

        comment.update(request.getBody());
        return commentRepository.save(comment);
    }

    // 댓글 정보 삭제 REST API
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    // 게시글과 댓글 정보를 한번에 조회하는 REST API <- Article 부분에 작성

}
