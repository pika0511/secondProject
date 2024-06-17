package com.example.secondProject.dto.myBatis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDtoM {
    private Long id;
//    private Long articleId;
    private String nickname;
    private String body;
    private String article_id;

//    public static CommentDtoM createCommentDto(Comment comment) {
//        return new CommentDtoM(
//                comment.getId(),
////                comment.getArticle().getId(),
//                comment.getNickname(),
//                comment.getBody()
//        );
//    }


}
