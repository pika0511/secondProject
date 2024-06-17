package com.example.secondProject.mapper;

import com.example.secondProject.dto.ArticleForm;
import com.example.secondProject.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface ArticleMapper {

    ArrayList<HashMap<String, Object>> findAll();

    ArticleForm findById(Long id);

    Article findById2(Long id);

    int save(ArticleForm articleForm);

    Long update(ArticleForm article);

    Long delete(Long id);

    int nextVal_articleId();
}
