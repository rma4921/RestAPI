package com.estsoft.blog.external.controller;

import com.estsoft.blog.external.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExternalController {
    private final ExternalService externalService;

    @GetMapping("/api/external/article")
    public ResponseEntity<String> externalApiArticle() {
        externalService.saveArticles();
        return ResponseEntity.ok("외부 API 데이터 저장 완료");
    }

    @GetMapping("/api/external/comment")
    public ResponseEntity<String> externalApiComment() {
        externalService.saveComments();
        return ResponseEntity.ok("외부 API 데이터 저장 완료");
    }
}
