package com.example.secondProject.service.myBatis;

import com.example.secondProject.dto.ArticleForm;
import com.example.secondProject.dto.myBatis.ArticleDTO;
import com.example.secondProject.dto.myBatis.CommentDtoM;
import com.example.secondProject.entity.Article;
import com.example.secondProject.mapper.ArticleMapper;
import com.example.secondProject.mapper.CommentMapper;
import com.example.secondProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class ArticleServiceM {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;

    public ArrayList<HashMap<String, Object>> index() {
        return articleMapper.findAll();
    }

    public ArrayList<HashMap<String, Object>> show(Long id) {
        return articleMapper.findById(id);
    }

    public Article show2(Long id) {
        return articleMapper.findById2(id);
    }

    public int create(ArticleForm dto) {
        Article article = dto.toEntity();
        return articleMapper.save(article);
    }

//    public Long update(Long id, ArticleForm dto) {
    public Long update(ArticleForm dto) {
        Article article = dto.toEntity();
//        Article target = articleMapper.findById2(id);
//        if(target==null || !id.equals(article.getId())){
//        if(target==null || id != article.getId()){
//            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
//            return 0;    // 응답은 컨트롤러가 하므로, 여기서는 null 반환
//        }
        return articleMapper.update(article);
//        target.patch(article);      //  --> 이건 일부분만 수정할 경우 엔티티를 셋팅하는 것 같음.
//        Article updated = articleRepository.save(target);
//        return updated;     // 응답은 컨트롤러가 하므로, 여기서는 수정데이터만 반환
    }

    public Long delete(Long id) {
        Article target = articleMapper.findById2(id);
        if(target == null){
            return null;
        }
        return articleMapper.delete(target.getId());
    }

    public ArticleDTO delete2(Long id) {
        // article이 있는지 조회
        Article article = show2(id);
        // 리턴 데이터 셋팅
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setResultCode("S0003");
        articleDTO.setRes(article);
        // 댓글이 있다면, 댓글도 삭제
        List<CommentDtoM> commentDtoM = commentMapper.findByArticleId(id);
        log.info("commentDtoM.size() : " + commentDtoM.size());
        for(var i=0; i<commentDtoM.size(); i++){
            commentMapper.delete2(id);
        }
        // 게시글 삭제
        articleMapper.delete(id);
        return articleDTO;

        
    }
}
