package com.example.secondProject.service;

import com.example.secondProject.dto.ArticleForm;
import com.example.secondProject.entity.Article;
import com.example.secondProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        Article target = articleRepository.findById(id).orElse(null);
        if(target==null || !id.equals(article.getId())){
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;    // 응답은 컨트롤러가 하므로, 여기서는 null 반환
        }
        target.patch(article);      //  --> 이건 일부분만 수정할 경우 엔티티를 셋팅하는 것 같음.
        Article updated = articleRepository.save(target);
        return updated;     // 응답은 컨트롤러가 하므로, 여기서는 수정데이터만 반환
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null){
            return null;
        }
        articleRepository.delete(target);
        return target;
    }
}
