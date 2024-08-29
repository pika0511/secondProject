package com.example.secondProject.controller;

import com.example.secondProject.dto.ArticleForm;
import com.example.secondProject.dto.CommentDto;
import com.example.secondProject.entity.Article;
import com.example.secondProject.repository.ArticleRepository;
import com.example.secondProject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/nxweb")
    public String nxWeb(){
        return "html/NxWeb_Guide2.html";
    }

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        Article article = form.toEntity();
        Article saved = articleRepository.save(article);
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentsDtos);
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleEntityList", articleEntityList);
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        Article articleEntity = form.toEntity();
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if(target != null){
            articleRepository.save(articleEntity);      // 엔티티를 DB에 저장(갱신)
        }
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article target = articleRepository.findById(id).orElse(null);
        if(target != null){
            List<CommentDto> comments = commentService.comments(id);
            if(comments.size() > 0){
                for(int i=0; i<comments.size(); i++){
                    CommentDto commentDto = comments.get(i);
                    commentService.delete(commentDto.getId());
                }
            }
            articleRepository.deleteById(id);
            rttr.addFlashAttribute("msg", "삭제됐습니다.");
        }
        return "redirect:/articles";
    }
}
