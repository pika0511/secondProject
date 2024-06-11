package com.example.secondProject.controller.myBatis;

import com.example.secondProject.dto.myBatis.CommentDtoM;
import com.example.secondProject.service.CommentService;
import com.example.secondProject.service.myBatis.CommentServiceM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mb/comments/")
public class CommentApiControllerM {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentServiceM commentServiceM;

    //    1. 댓글 조회
    @GetMapping("{articleId}")
    public ResponseEntity<List<CommentDtoM>> comments(@PathVariable Long articleId){
        List<CommentDtoM> dtos = commentServiceM.comments(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
//    2. 댓글 생성
    @PostMapping("{articleId}/create")
    public ResponseEntity<?> create(@PathVariable Long articleId, @RequestBody CommentDtoM dtoM){
        int created = commentServiceM.create(articleId, dtoM);
        return (created == 1) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//    3. 댓글 수정
    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody CommentDtoM dto){
        int updated = commentServiceM.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
//    4. 댓글 삭제
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        int deleted = commentServiceM.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }

}
