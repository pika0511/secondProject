package com.example.secondProject.repository;

import com.example.secondProject.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;

    @Test
    @DisplayName("모든 게시글 조회")
    void findAll(){
        // 1. 입력 데이터 준비
        // 2. 실제 데이터
        List<Article> articles = articleRepository.findAll();
        // 3. 예상 데이터
        System.out.println("findAll : " + articles.toString());
        // 4. 비교 및 검증
    }

}