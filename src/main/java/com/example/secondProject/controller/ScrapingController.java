package com.example.secondProject.controller;

import com.example.secondProject.dto.myBatis.Service;
import com.example.secondProject.service.myBatis.ScrapingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class ScrapingController {
    @Autowired
    ScrapingService scrapingService;

    // Scraping 페이지 호출
    @GetMapping("/scraping")
    public String scraping(){
        return "scrapingPage/scraping";
    }

    // 입력값 호출
    @GetMapping("/scraping/{svcId}")
    @ResponseBody       // 리턴값이 뷰 이름이 아닌 responseBody에 귀속된다는 것을 알리는 애노테이션
    public String callInput(@PathVariable String svcId, Model model){
        Service service = scrapingService.findById(svcId);
//        List<CommentDto> commentsDtos = scrapingService.comments(svcId);
        model.addAttribute("service", service);
//        model.addAttribute("commentDtos", commentsDtos);

        log.info("=================================");
        log.info(service.toString());
        log.info("=================================");
        return service.getInJson();
    }
}
