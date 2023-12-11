package com.news.NS.service;

import cn.dev33.satoken.stp.StpUtil;
import com.news.NS.common.AlertException;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.News;
import com.news.NS.domain.Section;
import com.news.NS.domain.dto.NewsCreateDTO;
import com.news.NS.mapper.NewsDynamicSqlSupport;
import com.news.NS.mapper.NewsMapper;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.mybatis.dynamic.sql.util.kotlin.spring.NamedParameterJdbcTemplateExtensionsKt.delete;

@Service
public class NewsService {
    private final NewsMapper newsMapper;

    public NewsService(NewsMapper newsMapper){this.newsMapper = newsMapper;}
    public boolean create(NewsCreateDTO news) {
        News temp = new News();
        temp.setSectionId(news.getSectionId());
        temp.setTitle(news.getTitle());
        temp.setContent(news.getContent());
        temp.setPublishStatus(CommonConstant.NEWS_NOTISSUE);
        temp.setPublisherId((Integer) StpUtil.getSession().get("userId"));
        temp.setNewsViews(0);
        temp.setLikeNumber(0);
        return (newsMapper.insert(temp) == 1);
    }

    public boolean delete(String ids) {
        System.out.println(ids);
        if(ids.charAt(ids.length()-1) != ','){
            ids = ids + ",";
        }
        String[] sts = ids.split(",");
        int res = 0;
        int[] id = new int[sts.length];
        for (int i=0;i<id.length;i++) {
            id[i] = Integer.parseInt(sts[i]);
        }
        for (int i=0;i<id.length;i++) {
            UpdateStatementProvider updateStatement = update(NewsDynamicSqlSupport.news)
                    .set(NewsDynamicSqlSupport.publishStatus).equalTo(CommonConstant.NEWS_DISABLE)
                    .where(NewsDynamicSqlSupport.newsId,isEqualTo(id[i]))
                    .build().render(RenderingStrategies.MYBATIS3);
            if(newsMapper.update(updateStatement) == 0){
                throw new AlertException(600,"删除新闻失败");
            }
        }
        return true;
    }

    public boolean publish(int newsId) {
        LocalDateTime currentTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentTime);
        String formattedDateTime = timestamp.toString();
        UpdateStatementProvider updateStatement = update(NewsDynamicSqlSupport.news)
                .set(NewsDynamicSqlSupport.publishStatus).equalTo(CommonConstant.NEWS_ISSUE)
                .set(NewsDynamicSqlSupport.publishTime).equalTo(timestamp)
                .where(NewsDynamicSqlSupport.newsId,isEqualTo(newsId))
                .build().render(RenderingStrategies.MYBATIS3);
        int rows = newsMapper.update(updateStatement);
        if(rows == 0){
            throw new AlertException(600,"更新失败");
        } else {
            return true;
        }
    }

    public Map<String,Object> getNewsById(Integer id){
        SelectStatementProvider sqlStatement = select(NewsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.newsId,isEqualTo(id))
                .build().render(RenderingStrategies.MYBATIS3);
        Optional<News> optional = newsMapper.selectByPrimaryKey(id);
        if(optional.isPresent()){
            News news = optional.get();
            if(news.getPublishStatus() == CommonConstant.NEWS_DISABLE){
                throw new AlertException(602,"新闻已删除");
            } else {
                Map<String,Object> map = new HashMap<>();
                map.put("news",news);
                updateViews(news);
                return map;
            }
        } else {
            throw new AlertException(601,"新闻不存在");
        }
    }

    public void updateViews(News news){
        UpdateStatementProvider updateStatement = update(NewsDynamicSqlSupport.news)
                .set(NewsDynamicSqlSupport.newsViews).equalTo(news.getNewsViews()+1)
                .where(NewsDynamicSqlSupport.newsId,isEqualTo(news.getNewsId()))
                .build().render(RenderingStrategies.MYBATIS3);
        newsMapper.update(updateStatement);
    }

    public Map<String,Object> getNewsBySectionId(Integer sectionId){
        SelectStatementProvider sqlStatement = select(NewsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.sectionId,isEqualTo(sectionId))
                .build().render(RenderingStrategies.MYBATIS3);
        List<News> newsList = newsMapper.selectMany(sqlStatement);
        Map<String,Object> map = new HashMap<>();
        map.put("total",newsList.size());
        map.put("newsList",newsList);
        return map;
    }
}
