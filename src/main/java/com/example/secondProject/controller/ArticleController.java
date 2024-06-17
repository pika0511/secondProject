package com.example.secondProject.controller;

import com.example.secondProject.dto.ArticleForm;
import com.example.secondProject.dto.myBatis.CommentDtoM;
import com.example.secondProject.entity.Article;
import com.example.secondProject.repository.ArticleRepository;
import com.example.secondProject.service.CommentService;
import com.example.secondProject.service.myBatis.ArticleServiceM;
import com.example.secondProject.service.myBatis.CommentServiceM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleServiceM articleServiceM;
    @Autowired
    private CommentServiceM commentServiceM;

    @GetMapping("/nxweb")
    public String nxWeb(){
        return "html/NxWeb_Guide2.html";
    }

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String create(ArticleForm form){
        int maxVal = articleServiceM.nextVal_articleId();
        int saved = articleServiceM.create(form);
        return "redirect:/articles/" + maxVal;
    }

    // jpa 방식
//    @PostMapping("/articles/create")
//    public String createArticle(ArticleForm form){
//        Article article = form.toEntity();
//        Article saved = articleRepository.save(article);
//        return "redirect:/articles/" + saved.getId();
//    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article article = articleServiceM.show2(id);
        List<CommentDtoM> commentsDtos = commentServiceM.comments(id);
        model.addAttribute("article", article);
        model.addAttribute("commentDtos", commentsDtos);
        log.info("commentsDtos : "+commentsDtos);
        return "articles/show";
    }

    // JPA사용 방식
//    @GetMapping("/articles/{id}")
//    public String show(@PathVariable Long id, Model model){
//        Article articleEntity = articleRepository.findById(id).orElse(null);
//        List<CommentDto> commentsDtos = commentService.comments(id);
//        model.addAttribute("article", articleEntity);
//        model.addAttribute("commentDtos", commentsDtos);
//        return "articles/show";
//    }

    @GetMapping("/articles")
    public String index(Model model){
        ArrayList<HashMap<String, Object>> articleList = articleServiceM.index();
        model.addAttribute("articleEntityList", articleList);
        return "articles/index";
    }

    //JPA방식
//    @GetMapping("/articles")
//    public String index(Model model){
//        ArrayList<Article> articleEntityList = articleRepository.findAll();
//        model.addAttribute("articleEntityList", articleEntityList);
//        return "articles/index";
//    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article article = articleServiceM.show2(id);
        model.addAttribute("article", article);
        return "articles/edit";
    }

    // jpa방식
//    @GetMapping("/articles/{id}/edit")
//    public String edit(@PathVariable Long id, Model model){
//        Article articleEntity = articleRepository.findById(id).orElse(null);
//        model.addAttribute("article", articleEntity);
//        return "articles/edit";
//    }

    @PostMapping("/articles/update")
    public String update(ArticleForm article){
        Article target = articleServiceM.show2(article.getId());

        if(target != null){

            articleServiceM.update(article);
        }
        return "redirect:/articles/"+target.getId();
    }

    // jpa 방식
//    @PostMapping("/articles/update")
//    public String update(ArticleForm form){
//        Article articleEntity = form.toEntity();
//        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
//        if(target != null){
//            articleRepository.save(articleEntity);      // 엔티티를 DB에 저장(갱신)
//        }
//        return "redirect:/articles/"+articleEntity.getId();
//    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article target = articleServiceM.show2(id);
        if(target != null){
            List<CommentDtoM> comments = commentServiceM.comments(id);
            if(comments.size() > 0){
                for(int i=0; i<comments.size(); i++){
                    CommentDtoM commentDtoM = comments.get(i);
                    commentServiceM.delete(commentDtoM.getId());
                }
            }
            articleServiceM.delete(id);
            rttr.addFlashAttribute("msg", "삭제됐습니다.");
        }
        return "redirect:/articles";
    }
    // jpa방식
//    @GetMapping("/articles/{id}/delete")
//    public String delete(@PathVariable Long id, RedirectAttributes rttr){
//        Article target = articleRepository.findById(id).orElse(null);
//        if(target != null){
//            List<CommentDtoM> comments = commentService.comments(id);
//            if(comments.size() > 0){
//                for(int i=0; i<comments.size(); i++){
//                    CommentDtoM commentDtoM = comments.get(i);
//                    commentService.delete(commentDtoM.getId());
//                }
//            }
//            articleRepository.deleteById(id);
//            rttr.addFlashAttribute("msg", "삭제됐습니다.");
//        }
//        return "redirect:/articles";
//    }
}
