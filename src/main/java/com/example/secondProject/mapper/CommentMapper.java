package com.example.secondProject.mapper;

import com.example.secondProject.dto.myBatis.CommentDtoM;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    List<CommentDtoM> findByArticleId(Long articleId);

    int save(CommentDtoM dtoM);

    int update(CommentDtoM dto);

    int delete(Long id);

    int delete2(Long articleId);

}
