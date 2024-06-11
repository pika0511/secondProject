package com.example.secondProject.controller.myBatis;

import com.example.secondProject.dto.ArticleForm;
import com.example.secondProject.dto.myBatis.ArticleDTO;
import com.example.secondProject.entity.Article;
import com.example.secondProject.service.ArticleService;
import com.example.secondProject.service.myBatis.ArticleServiceM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/mb/articles/")
public class ArticleApiControllerM {
    private static final Logger log = LoggerFactory.getLogger(ArticleApiControllerM.class);
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleServiceM articleServiceM;

    // 1. 게시글 전체 보기
    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    public ResponseEntity<?> index(){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setResultCode("S0001");
        articleDTO.setRes(articleServiceM.index());
        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }

    // 2. 게시글 1개 보기
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> show(@PathVariable Long id){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setResultCode("S0002");
        articleDTO.setRes(articleServiceM.show(id));
        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }

    // 3. 게시글 생성하기
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ArticleForm dto){
        int created = articleServiceM.create(dto);
//        log.info(String.valueOf(created));
        return (created == 1) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 4. 게시글 업데이트 하기
    @RequestMapping(value = "update", method = RequestMethod.POST)
//    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ArticleForm dto){
    public ResponseEntity<?> update(@RequestBody ArticleForm dto){
        Long updated = articleServiceM.update(dto);
        return (updated == 1) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 5. 게시글 삭제하기
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Article article = articleServiceM.show2(id);
        Long deleted = articleServiceM.delete(id);
        log.info("deletedAAA : " + deleted);
        return (deleted == 1) ?
                ResponseEntity.ok(article) :
                ResponseEntity.badRequest().build();
//        ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
//        ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 6. 게시글 삭제하기(2)
    @DeleteMapping("delete2/{id}")
    public ResponseEntity<?> delete2(@PathVariable Long id){
        Long deleted = articleServiceM.delete2(id);
        log.info("deletedAAA : " + deleted);
        return (deleted == 1) ?
                ResponseEntity.ok().build() :
//                ResponseEntity.ok(articleDTO) :
                ResponseEntity.badRequest().build();
//        ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
//        ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    ArticleDTO articleDTO = new ArticleDTO();
//    articleDTO.setResultCode("S0001");
//    articleDTO.setRes(articleServiceM.index());
//    return new ResponseEntity<>(articleDTO, HttpStatus.OK);
}
