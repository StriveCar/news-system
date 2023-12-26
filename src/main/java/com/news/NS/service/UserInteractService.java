package com.news.NS.service;


import com.news.NS.common.AlertException;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.Collect;
import com.news.NS.domain.News;
import com.news.NS.domain.dto.UserInteract.UserFocusDTO;
import com.news.NS.domain.vo.CollectNewsVo;
import com.news.NS.domain.vo.FocusVo;
import com.news.NS.mapper.*;
import com.news.NS.mapper.focusMapper.FocusMapper;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mybatis.dynamic.sql.SqlBuilder.*;


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

    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String,Object> addLikes(Integer newsId,Integer userId) {
        //获取原点赞数
        Integer likeNumber = newsMapper.selectLikeNumber(newsId);
        //游客不能点赞
        if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(userId))) <= 0)
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该用户不存在");

        Map<String,Object> result=new HashMap<>();
        //查得到新闻数据就增加点赞数
        if (likeNumber != null) {
            UpdateStatementProvider updateStatementProvider = update(NewsDynamicSqlSupport.news)
                    .set(NewsDynamicSqlSupport.likeNumber)
                    .equalTo(likeNumber + 1)
                    .where(NewsDynamicSqlSupport.newsId, isEqualTo(newsId))
                    .build()
                    .render(RenderingStrategies.MYBATIS3);
           if(newsMapper.update(updateStatementProvider) >= 0)
               result.put("result","点赞成功");
           else
               result.put("result","点赞失败");

        } else {
            result.put("result","新闻不存在");
        }
        return result;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> addCollectInfo(Collect collect) {
        Integer newsId = collect.getNewsId();
        Integer userId = collect.getUserId();
        if (newsMapper.count(c -> c.where(NewsDynamicSqlSupport.newsId, isEqualTo(newsId)).and(NewsDynamicSqlSupport.publishStatus,isEqualTo(CommonConstant.NEWS_ISSUE))) <= 0) { // 数据库不存在该 newsId
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该新闻不存在");
        }
        if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(userId))) <= 0) {
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该用户不存在");
        }

        if (collectMapper.count(c -> c.where(CollectDynamicSqlSupport.userId, isEqualTo(userId)).and(CollectDynamicSqlSupport.newsId, isEqualTo(newsId))) > 0) {
            throw new AlertException(500, "该新闻已被该用户收藏");
        }
        Map<String,Object> result=new HashMap<>();
        if (collectMapper.insert(collect)<0)
            result.put("result","收藏成功");
        else
            result.put("result","收藏失败");

        return result;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> deletCollectInfo(Collect collect) {
        Integer userId = collect.getUserId();
        Integer newsId = collect.getNewsId();
        if (collectMapper.count(c -> c.where(CollectDynamicSqlSupport.userId, isEqualTo(userId)).and(CollectDynamicSqlSupport.newsId, isEqualTo(newsId))) <= 0) {
            throw new AlertException(500, "用户不存在或新闻不存在或该新闻本来就没被收藏");
        }
        Map<String,Object> result=new HashMap<>();
        if(collectMapper.deleteByPrimaryKey(userId, newsId) >0 )
            result.put("result","取消收藏成功");
        else
            result.put("result","取消收藏失败");
        return result;



    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String,Object> focusUser(UserFocusDTO userFocusDTO) {
        if (focusMapper.getOneFocusInfo(userFocusDTO) != null)
            throw new AlertException(500, "不能重复关注");

        if(userFocusDTO.getUserId() == userFocusDTO.getFocusedUserId())
            throw new AlertException(500,"不能关注自己");

        if(userMapper.count(c->c.where(UserDynamicSqlSupport.userId,isEqualTo(userFocusDTO.getUserId()))) <=0 ||
                userMapper.count(d->d.where(UserDynamicSqlSupport.userId,isEqualTo(userFocusDTO.getFocusedUserId()))) <= 0)
            throw new AlertException(500,"关注或被关注者不存在");

        Map<String,Object> result=new HashMap<>();
        if (focusMapper.focusUser(userFocusDTO)>0)
            result.put("result","关注成功");
        else
            result.put("result","关注失败");

        return  result;
    }


    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String,Object> unfocusUser(UserFocusDTO userFocusDTO) {
        if (focusMapper.getOneFocusInfo(userFocusDTO) == null)
            throw new AlertException(500, "用户不存在或您本来就没有关注该用户");

        Map<String,Object> result=new HashMap<>();
        if(focusMapper.unfocusUser(userFocusDTO)>0)
            result.put("result","取消关注成功");
        else
            result.put("result","取消关注失败");

        return result;

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

    public List<CollectNewsVo> getCollectNewsList(Integer userId) {
        //先获取收藏的新闻的id列表
        List<Integer> newsIdList=collectMapper.selectNewsIdsByUserId(userId);

        //新闻列表
        List<CollectNewsVo> result=new ArrayList<>();

        for(Integer newsId : newsIdList){
            CollectNewsVo collectNewsVo = new CollectNewsVo();
            //根据新闻id获取新闻的部分信息
            News news=newsMapper.selectOneNews(newsId);
            collectNewsVo.setNewsId(newsId);
            collectNewsVo.setTitle(news.getTitle());
            collectNewsVo.setContent(news.getContent());


            collectNewsVo.setPublisherName(userMapper.selectUsernameById(news.getPublisherId()));
            collectNewsVo.setPublisherAvatarUrl(userMapper.selectAvatarUrlById(news.getPublisherId()));
            collectNewsVo.setSectionName(sectionMapper.getNameById(news.getSectionId()));
            result.add(collectNewsVo);

        }

        return result;
    }
}
