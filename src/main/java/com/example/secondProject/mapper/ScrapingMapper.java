package com.example.secondProject.mapper;

import com.example.secondProject.dto.myBatis.Service;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ScrapingMapper {
    Service findById(String svcId);
}
