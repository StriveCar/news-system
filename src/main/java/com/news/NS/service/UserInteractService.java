package com.news.NS.service;


import com.news.NS.common.AlertException;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.Collect;
import com.news.NS.domain.dto.UserInteract.UserFocusDTO;
import com.news.NS.mapper.*;
import com.news.NS.mapper.focusMapper.FocusMapper;
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

    @Autowired
    CollectMapper collectMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    FocusMapper focusMapper;

    public boolean addLikes(Integer newsId) {
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
            return true;
        } else {
            return false;
        }


    }

    public int addCollectInfo(Collect collect) {
        Integer newsId = collect.getNewsId();
        Integer userId = collect.getUserId();
        if (newsMapper.count(c -> c.where(NewsDynamicSqlSupport.newsId, isEqualTo(newsId))) <= 0) { // 数据库不存在该 newsId
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该新闻不存在");
        } else if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(userId))) <= 0) {
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该用户不存在");
        } else if (collectMapper.count(c -> c.where(CollectDynamicSqlSupport.userId, isEqualTo(userId)).and(CollectDynamicSqlSupport.newsId, isEqualTo(newsId))) > 0) {
            throw new AlertException(500, "该新闻已被该用户收藏");
        } else return collectMapper.insert(collect);
    }

    public int deletCollectInfo(Collect collect) {
        Integer userId = collect.getUserId();
        Integer newsId = collect.getNewsId();
        if (collectMapper.count(c -> c.where(CollectDynamicSqlSupport.userId, isEqualTo(userId)).and(CollectDynamicSqlSupport.newsId, isEqualTo(newsId))) <= 0) {
            throw new AlertException(500, "用户不存在或新闻不存在或该新闻本来就没被收藏");
        }

        return collectMapper.deleteByPrimaryKey(userId, newsId);


    }

    public int focusUser(UserFocusDTO userFocusDTO) {
        if (focusMapper.getOneFocusInfo(userFocusDTO) != null)
            throw new AlertException(500, "不能重复关注");

        return focusMapper.focusUser(userFocusDTO);
    }


    public int unfocusUser(UserFocusDTO userFocusDTO) {
        if (focusMapper.getOneFocusInfo(userFocusDTO) == null)
            throw new AlertException(500, "用户不存在或您本来就没有关注该用户");
        return focusMapper.unfocusUser(userFocusDTO);
    }
}
