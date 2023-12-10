package com.news.NS.service;

import com.news.NS.domain.News;
import com.news.NS.mapper.NewsDynamicSqlSupport;
import com.news.NS.mapper.NewsMapper;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.mybatis.dynamic.sql.util.kotlin.spring.NamedParameterJdbcTemplateExtensionsKt.delete;

@Service
public class NewsService {
    private final NewsMapper newsMapper;
    @Autowired
    public NewsService(NewsMapper newsMapper){this.newsMapper = newsMapper;}
    public boolean create(@RequestParam News news) {
        News temp = news;
        // 1 = 草稿
        temp.setPublishStatus(Byte.parseByte("1"));
        return (newsMapper.insert(temp) == 1);
    }

    public int delete(String ids) {
        String[] ch = ids.split(",");
        int res = 0;
        for (String i:ch) {
            res += newsMapper.deleteByPrimaryKey(Integer.parseInt(i));
        }
        return res;
    }

    public boolean publish(int newsId) {
        UpdateStatementProvider updateStatement = update(NewsDynamicSqlSupport.news)
        newsMapper.update()
        return true;
    }

    public Map<String,Object> getNewsBySectionId(@RequestParam Integer sectionId){
        SelectStatementProvider sqlStatement = select(NewsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .build().render(RenderingStrategies.MYBATIS3);
        List<News> newsList = newsMapper.selectMany(sqlStatement);
        Map<String,Object> map = new HashMap<>();
        map.put("total",newsList.size());
        map.put("newsList",newsList);
        return map;
    }
}
