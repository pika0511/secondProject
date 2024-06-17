package com.example.secondProject.mapper;

import com.example.secondProject.dto.myBatis.SearchDto;

public interface PostMapper {
    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
//    List<PostResponse> findAll(SearchDto params);

    /**
     * 게시글 카운팅
     * @return 게시글 수
     */
    int count(SearchDto params);
}
