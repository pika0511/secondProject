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

    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    public ResponseEntity<?> index(){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setResultCode("S0001");
        articleDTO.setRes(articleServiceM.index());
        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> show(@PathVariable Long id){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setResultCode("S0002");
        articleDTO.setRes(articleServiceM.show(id));
        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ArticleForm dto){
        int created = articleServiceM.create(dto);
//        log.info(String.valueOf(created));
        return (created == 1) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
//    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ArticleForm dto){
    public ResponseEntity<?> update(@RequestBody ArticleForm dto){
        Long updated = articleServiceM.update(dto);
        return (updated == 1) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Long deleted = articleServiceM.delete(id);
        log.info("deletedAAA : " + deleted);
        return (deleted == 1) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
