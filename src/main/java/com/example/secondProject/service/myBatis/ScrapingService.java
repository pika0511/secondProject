package com.example.secondProject.service.myBatis;

import com.example.secondProject.mapper.ScrapingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrapingService {
    @Autowired
    ScrapingMapper scrapingMapper;
    public com.example.secondProject.dto.myBatis.Service findById(String svcId){
        return scrapingMapper.findById(svcId);
    }
}
