package com.news.NS.service;

import com.news.NS.common.CommonConstant;
import com.news.NS.domain.vo.NewsStatusVo;
import com.news.NS.domain.vo.PulisherDataVo;
import com.news.NS.domain.vo.SectionNewsVo;
import com.news.NS.mapper.*;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private SectionService sectionService;

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

    public Map<String, Object> getPulisherTop(){
        List<PulisherDataVo> pulisherDataVos = userMapper.pulisherData();;
        List<String> names = pulisherDataVos.stream()
                .map(PulisherDataVo::getUsername)
                .collect(Collectors.toList());
        List<Integer> viewsSum = pulisherDataVos.stream()
                .map(PulisherDataVo::getViewsSum)
                .collect(Collectors.toList());
        List<Integer> likeSum = pulisherDataVos.stream()
                .map(PulisherDataVo::getLikeSum)
                .collect(Collectors.toList());
        List<Integer> newsCount = pulisherDataVos.stream()
                .map(PulisherDataVo::getNewsCount)
                .collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("name",names);
        map.put("viewsSum",viewsSum);
        map.put("likeSum",likeSum);
        map.put("newsCount",newsCount);
        return map;
    }

    public Map<String, Object> getRolesCount(){
        List<Integer> count = userMapper.selectRoleCount();
        Map<String, Object> map = new HashMap<>();
        map.put("roleCount",count);
        return map;
    }

    public Map<String, Object> getSectionData(){
        List<SectionNewsVo> sectionNewsVos= sectionService.querySectionDataList(1,10).getPageData();
        List<String> names = sectionNewsVos.stream()
                .map(SectionNewsVo::getSectionName)
                .collect(Collectors.toList());
        List<Integer> viewsSum = sectionNewsVos.stream()
                .map(SectionNewsVo::getViewsSum)
                .collect(Collectors.toList());
        List<Integer> likeSum = sectionNewsVos.stream()
                .map(SectionNewsVo::getLikeSum)
                .collect(Collectors.toList());
        List<Integer> newsCount = sectionNewsVos.stream()
                .map(SectionNewsVo::getNewsNum)
                .collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("name",names);
        map.put("viewsSum",viewsSum);
        map.put("likeSum",likeSum);
        map.put("newsCount",newsCount);
        return map;
    }

    public Map<String, Object> getNewsStatusData(){
        Map<Integer, String> statusMapping = new HashMap<>();
        statusMapping.put(1, "未发布");
        statusMapping.put(2, "已发布");
        statusMapping.put(3, "取消发布");
        statusMapping.put(4, "禁用");

        List<NewsStatusVo> newsStatusVos = newsMapper.selectStatusData();
        List<Integer> count = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<String> statusList = new ArrayList<>();
        for (NewsStatusVo status : newsStatusVos) {
            int publishStatus = status.getPublishStatus();
            String chineseStatus = statusMapping.getOrDefault(publishStatus, "未知状态");
            statusList.add(chineseStatus);
            count.add(status.getCount());
        }
        map.put("status",statusList);
        map.put("count",count);
        return map;
    }
}
