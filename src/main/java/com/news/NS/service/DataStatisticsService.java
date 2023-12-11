package com.news.NS.service;

import com.news.NS.common.CommonConstant;
import com.news.NS.domain.vo.PulisherDataVo;
import com.news.NS.mapper.*;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @author 车
 * @date 2023/12/9 12 14
 * discription
 */
@Service
public class DataStatisticsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private FirstCommentMapper firstCommentMapper;

    @Autowired
    private SecondCommentMapper secondCommentMapper;

    public Map<String, Object> getSystemCount(){
        SelectStatementProvider userCount = select(count())
                .from(UserDynamicSqlSupport.user)
                .build().render(RenderingStrategies.MYBATIS3);
        SelectStatementProvider pulisherCount = select(count())
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.identification,isEqualTo(CommonConstant.PULISHER_ROLE))
                .build().render(RenderingStrategies.MYBATIS3);
        SelectStatementProvider newsCount = select(count())
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.publishStatus,isEqualTo(CommonConstant.NEWS_ISSUE))
                .build().render(RenderingStrategies.MYBATIS3);
        SelectStatementProvider firstCommentCOunt = select(count())
                .from(FirstCommentDynamicSqlSupport.firstComment)
                .build().render(RenderingStrategies.MYBATIS3);
        SelectStatementProvider SecondCommentCOunt = select(count())
                .from(SecondCommentDynamicSqlSupport.secondComment)
                .build().render(RenderingStrategies.MYBATIS3);
        Map<String, Object> map = new HashMap<>();
        map.put("userCount",userMapper.count(userCount));
        map.put("pulisherCount",userMapper.count(pulisherCount));
        map.put("newsCount",newsMapper.count(newsCount));
        map.put("commentCount",firstCommentMapper.count(firstCommentCOunt)+ secondCommentMapper.count(SecondCommentCOunt));
        map.put("viewsCount",newsMapper.selectData());
        return map;
    }

    public List<PulisherDataVo> getPulisherTop(){
        return userMapper.pulisherData();
    }

//    public Map<String, Object> get
}
