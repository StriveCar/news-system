package com.news.NS.service;



import com.news.NS.mapper.NewsMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserInteractService {

    @Autowired
      NewsMapper newsMapper;

    public boolean addLikes(){
/*
        UpdateStatementProvider updateStatement=update(NewsDynamicSqlSupport.news);
*/
        return true;
    }
}
