package com.news.NS.service;



import com.news.NS.common.AlertException;
import com.news.NS.mapper.NewsDynamicSqlSupport;
import com.news.NS.mapper.NewsMapper;


import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.update;


@Service
public class UserInteractService {

    @Autowired
    NewsMapper newsMapper;

    public String addLikes(Integer newsId) {
        //获取原点赞数
        Integer likeNumber = newsMapper.selectLikeNumber(newsId);
        //查得到新闻数据就增加点赞数
        if (likeNumber != null) {
            UpdateStatementProvider updateStatementProvider = update(NewsDynamicSqlSupport.news)
                    .set(NewsDynamicSqlSupport.likeNumber)
                    .equalTo(likeNumber + 1)
                    .where(NewsDynamicSqlSupport.newsId, isEqualTo(newsId))
                    .build()
                    .render(RenderingStrategies.MYBATIS3);
            newsMapper.update(updateStatementProvider);
            return "点赞成功";
        } else {
            throw new AlertException(500,"新闻不存在，点赞失败");
        }


    }
}
