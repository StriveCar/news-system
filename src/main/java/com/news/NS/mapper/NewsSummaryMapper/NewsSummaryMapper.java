package com.news.NS.mapper.NewsSummaryMapper;

import com.news.NS.domain.vo.HotNewsVo;
import com.news.NS.domain.vo.BigPictureNewsVo;
import com.news.NS.domain.vo.NewsSummaryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface NewsSummaryMapper {

    List<HotNewsVo> getHotNews(Integer sectionId, Integer num);

    @Select("select distinct news_id from picture")
    List<Integer> selectPictureNewsId();


    List<BigPictureNewsVo> selectPictureNewsIdAndNewsTitle(List<Integer> ids, Integer sectionId, Integer num);

    @Select("select picture_url from picture where news_id=#{newsId} limit 1")
    String getPicturUrlByNewsId(Integer newsId);


    List<NewsSummaryVo> getBaseNewsInfo(Integer sectionId, Integer num);

}
