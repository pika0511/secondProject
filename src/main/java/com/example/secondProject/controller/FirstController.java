package com.example.secondProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "SB");
        return "greetings";
    }

    @GetMapping("/hi2")
    public String niceToMeetYou2(Model model){
        model.addAttribute("username", "SB");
        return "hello";
    }

    @GetMapping("/hi3")
    public String niceToMeetYou3(Model model){
        model.addAttribute("username", "SB");
//        ArrayList<Article> articleEntityList = articleRepository.findAll();
//        model.addAttribute("articleEntityList", articleEntityList);
        return "test/hi";
    }

    @GetMapping("/hello")
    public String niceToMeetYou4(Model model){
        model.addAttribute("username", "SB");
//        ArrayList<Article> articleEntityList = articleRepository.findAll();
//        model.addAttribute("articleEntityList", articleEntityList);
        return "articles/test.html";
    }

}
