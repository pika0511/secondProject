package com.example.secondProject.service.myBatis;

import com.example.secondProject.mapper.ScrapingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapingService {
    @Autowired
    ScrapingMapper scrapingMapper;
    public com.example.secondProject.dto.myBatis.Service findById(String svcId){
        return scrapingMapper.findById(svcId);
    }

    public List<com.example.secondProject.dto.myBatis.Service> getSelectMainMenu() {
        return scrapingMapper.getSelectMainMenu();
    }

    public List<com.example.secondProject.dto.myBatis.Service> getSelectSubMenu() {
        return scrapingMapper.getSelectSubMenu();
    }
}
