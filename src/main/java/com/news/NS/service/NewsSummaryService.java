package com.news.NS.service;

import com.news.NS.common.AlertException;
import com.news.NS.domain.vo.HotNewsVo;
import com.news.NS.domain.vo.BigPictureNewsVo;
import com.news.NS.domain.vo.NewsSummaryVo;
import com.news.NS.mapper.UserMapper;
import com.news.NS.mapper.NewsSummaryMapper.NewsSummaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class NewsSummaryService {
    @Autowired
    NewsSummaryMapper newsSummaryMapper;

    @Autowired
    UserMapper userMapper;

    public List<HotNewsVo> getHotNews(Integer sectionId, Integer num) {
        return newsSummaryMapper.getHotNews(sectionId,num);
    }

    public List<BigPictureNewsVo> getHomePictureNews(Integer sectionId, Integer num) {
        //先选出有图片的新闻ID
        List<Integer> pictureNewsId = newsSummaryMapper.selectPictureNewsId();


        if (pictureNewsId.size() == 0) {
            throw new AlertException(500, "没有图片新闻");
        }

        //再选出要展示的图片新闻的id和title
        List<BigPictureNewsVo> result = newsSummaryMapper.selectPictureNewsIdAndNewsTitle(pictureNewsId,sectionId,num);

        //最后在根据过滤出来的新闻，把封面拼接上去，封面默认是编辑新闻时最先上传的第一张图片

        result.forEach(item -> item.setPictureUrl(newsSummaryMapper.getPicturUrlByNewsId(item.getNewsId())));

        return result;
    }

    public List<NewsSummaryVo> getNewsSummary(Integer sectionId, Integer num) {

        //1.先获取新闻的信息,id,标题，发布时间，发布人id
        List<NewsSummaryVo> newsSummaryVoList = newsSummaryMapper.getBaseNewsInfo(sectionId,num);
        if (newsSummaryVoList.size() == 0) {
            throw new AlertException(500, "没有新闻");
        }

        //2.把发布人用户名，头像加入到新闻基本信息中
        for (NewsSummaryVo newsSummaryVo : newsSummaryVoList) {
            //2.1发布人头像
            newsSummaryVo.setPublisherAvatarUrl(userMapper.selectAvatarUrlById(newsSummaryVo.getPublisherId()));
            //2.2发布人昵称
            newsSummaryVo.setPublisherUserName(userMapper.selectUsernameById(newsSummaryVo.getPublisherId()));
            //2.3新闻封面
            newsSummaryVo.setNewsCoverUrl(newsSummaryMapper.getPicturUrlByNewsId(newsSummaryVo.getNewsId()));
        }
        return newsSummaryVoList;
    }
}
