package com.example.secondProject.service;

import com.example.secondProject.dto.ArticleForm;
import com.example.secondProject.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    ArticleMapper articleMapper;

    /**
     * 게시글 1000개 생성
     */
    @Test
    void saveByForeach() {
        for (int i = 1; i <= 1000; i++) {
            ArticleForm articleForm = new ArticleForm();
            articleForm.setTitle(i + "번 게시글 제목");
            articleForm.setContent(i + "번 게시글 내용");
            articleMapper.save(articleForm);
        }
    }
}
