package com.example.secondProject.mapper;

import com.example.secondProject.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface ArticleMapper {

    ArrayList<HashMap<String, Object>> findAll();

    ArrayList<HashMap<String, Object>> findById(Long id);

    Article findById2(Long id);

    int save(Article article);

    Long update(Article article);

    Long delete(Long id);
}
