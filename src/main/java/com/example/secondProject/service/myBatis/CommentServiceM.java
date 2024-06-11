package com.example.secondProject.service.myBatis;

import com.example.secondProject.dto.myBatis.CommentDtoM;
import com.example.secondProject.entity.Article;
import com.example.secondProject.entity.Comment;
import com.example.secondProject.mapper.CommentMapper;
import com.example.secondProject.repository.ArticleRepository;
import com.example.secondProject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceM {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentMapper commentMapper;

    public List<CommentDtoM> comments(Long articleId) {
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for(int i=0; i<comments.size(); i++){
//            Comment comment = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(comment);
//            dtos.add(dto);
//        }
//        return dtos;
        return commentMapper.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDtoM.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public int create(Long articleId, CommentDtoM dtoM) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성실패! " + "대상 게시글이 없습니다."));
        int createdRow = commentMapper.save(dtoM);
//        Comment comment = Comment.createComment(dtoM, article);
//        Comment created = commentRepository.save(comment);
        return createdRow;
    }

    public int update(CommentDtoM dto) {
//        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정실패!" + " 대상 댓글이 없습니다."));
//        target.patch(dto);
        int updated = commentMapper.update(dto);
        return updated;
    }

    public int delete(Long id) {
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제실패! " + "대상이 없습니다."));
        return commentMapper.delete(target.getId());
//        return CommentDto.createCommentDto(target);
    }
}
