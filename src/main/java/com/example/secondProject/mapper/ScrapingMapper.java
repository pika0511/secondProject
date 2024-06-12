package com.example.secondProject.mapper;

import com.example.secondProject.dto.myBatis.Service;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ScrapingMapper {
    Service findById(String svcId);

    List<Service> getSelectMainMenu();

    List<Service> getSelectSubMenu();
}
