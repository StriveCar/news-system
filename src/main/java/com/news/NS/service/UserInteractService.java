package com.news.NS.service;


import com.news.NS.common.AlertException;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.Collect;
import com.news.NS.domain.News;
import com.news.NS.domain.Section;
import com.news.NS.domain.dto.UserInteract.UserFocusDTO;
import com.news.NS.domain.vo.CollectNewsOv;
import com.news.NS.domain.vo.FocusVo;
import com.news.NS.mapper.*;
import com.news.NS.mapper.focusMapper.FocusMapper;
import io.swagger.models.auth.In;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    SectionMapper sectionMapper;

    public boolean addLikes(Integer newsId,Integer userId) {
        //获取原点赞数
        Integer likeNumber = newsMapper.selectLikeNumber(newsId);
        //游客不能点赞
        if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(userId))) <= 0)
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该用户不存在");

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

    public List<FocusVo> getFocusList(Integer userId) {
        if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(userId))) <= 0)
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该用户不存在");

        List<Integer> focusIds = focusMapper.getFocusIdList(userId);


        return focusMapper.getFocusVoList(focusIds);
    }

    public List<FocusVo> getFollowsList(Integer focusedUserId) {
        if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(focusedUserId))) <= 0)
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该用户不存在");

        List<Integer> followIds = focusMapper.getFollowsIdList(focusedUserId);

        return focusMapper.getFocusVoList(followIds);
    }

    public List<CollectNewsOv> getCollectNewsList(Integer userId) {
        //先获取收藏的新闻的id列表
        List<Integer> newsIdList=collectMapper.selectNewsIdsByUserId(userId);

        //新闻列表
        List<CollectNewsOv> result=new ArrayList<>();
        String sectionName="";
        String publisherAvatar="";
        String publisherName="";
        for(Integer newsId : newsIdList){
            CollectNewsOv collectNewsOv = new CollectNewsOv();
            //根据新闻id获取新闻的部分信息
            News news=newsMapper.selectOneNews(newsId);
            collectNewsOv.setNewsId(newsId);
            collectNewsOv.setTitle(news.getTitle());
            collectNewsOv.setContent(news.getContent());

/*            sectionName=sectionMapper.getNameById(news.getSectionId());
            publisherName=userMapper.selectUsernameById(news.getPublisherId());
            publisherAvatar=userMapper.selectAvatarUrlById(news.getPublisherId());*/

            collectNewsOv.setPublisherName(userMapper.selectUsernameById(news.getPublisherId()));
            collectNewsOv.setPublisherAvatarUrl(userMapper.selectAvatarUrlById(news.getPublisherId()));
            collectNewsOv.setSectionName(sectionMapper.getNameById(news.getSectionId()));
            result.add(collectNewsOv);

        }

        return result;
    }
}
