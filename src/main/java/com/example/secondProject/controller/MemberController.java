package com.example.secondProject.controller;

import com.example.secondProject.dto.MemberForm;
import com.example.secondProject.entity.Member;
import com.example.secondProject.repository.NewMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MemberController {
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private NewMemberRepository newMemberRepository;

    @GetMapping("/join/member")
    public String NewMemberForm(){
        return "join/newMember";
    }
    @PostMapping("/join/create")
    public String createMember(MemberForm form){
        Member member = form.toEntity();
        Member saved = newMemberRepository.save(member);
        return "";
    }
}
