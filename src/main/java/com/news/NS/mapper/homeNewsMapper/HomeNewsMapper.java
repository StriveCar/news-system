package com.news.NS.mapper.homeNewsMapper;

import com.news.NS.domain.vo.HomeHotNewsVo;
import com.news.NS.domain.vo.HomePictureNewsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HomeNewsMapper {


    @Select("select news_id,title from news order by (news.like_number*7+news.news_views*3) desc limit 10")
    List<HomeHotNewsVo> getHotNews();

    @Select("select distinct news_id from picture")
    List<Integer> selectPictureNewsId();


    List<HomePictureNewsVo> selectPictureNewsIdAndNewsTitle(List<Integer> ids);

    @Select("select picture_url from picture where news_id=#{newsId} limit 1")
    String getPicturUrlByNewsId(Integer newsId);
}
