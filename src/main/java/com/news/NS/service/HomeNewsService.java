package com.news.NS.service;

import com.news.NS.common.AlertException;
import com.news.NS.domain.vo.HomeHotNewsVo;
import com.news.NS.domain.vo.HomePictureNewsVo;
import com.news.NS.mapper.homeNewsMapper.HomeNewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeNewsService {
    @Autowired
    HomeNewsMapper homeNewsMapper;

    public List<HomeHotNewsVo> getHotNews() {
        return homeNewsMapper.getHotNews();
    }

    public List<HomePictureNewsVo> getHomePictureNews() {
        //先选出有图片的新闻ID
        List<Integer> pictureNewsId = homeNewsMapper.selectPictureNewsId();

        if (pictureNewsId.size() == 0) {
            throw new AlertException(500, "没有图片新闻");
        }

        //再选出要展示的图片新闻的id和title
        List<HomePictureNewsVo> result = homeNewsMapper.selectPictureNewsIdAndNewsTitle(pictureNewsId);

        //最后根据筛选出来的要展示的图片新闻的图片, 默认选择新闻中最先上传的图片

        result.forEach(item -> item.setPictureUrl(homeNewsMapper.getPicturUrlByNewsId(item.getNewsId())));

        return result;
    }
}
