package com.news.NS.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.NS.common.AlertException;
import com.news.NS.common.CommonConstant;
import com.news.NS.domain.Section;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.mapper.NewsDynamicSqlSupport;
import com.news.NS.mapper.SectionDynamicSqlSupport;
import com.news.NS.mapper.SectionMapper;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @author 车
 * @date 2023/12/6 20 49
 * discription
 */

@Service
public class SectionService {
    @Autowired
    private SectionMapper sectionMapper;

    public Section addSection(String sectionName) {
        SelectStatementProvider selectStatementProvider = select(count())
                .from(SectionDynamicSqlSupport.section)
                .where(SectionDynamicSqlSupport.sectionName, isEqualTo(sectionName))
                .build().render(RenderingStrategies.MYBATIS3);
        if (sectionMapper.count(selectStatementProvider) != 0) {
            throw new AlertException(ResultCode.SECTION_EXIST);
        }
        Section section = new Section();
        section.setSectionName(sectionName);

        if (sectionMapper.insert(section) == 0) {
            throw new AlertException(ResultCode.SYSTEM_ERROR);
        }

        return section;
    }

    public void delSection(Integer sectionId) {
        Optional<Section> optionalSection = sectionMapper.selectByPrimaryKey(sectionId);
        if (optionalSection.isPresent()) {
            if (sectionMapper.deleteByPrimaryKey(sectionId) == 0) {
                throw new AlertException(ResultCode.SYSTEM_ERROR);
            }
        } else {
            throw new AlertException(ResultCode.SECTION_NOT_EXIST);
        }
    }

    public void updateSection(Section section) {
        if (sectionMapper.updateByPrimaryKeySelective(section) == 0) {
            throw new AlertException(ResultCode.SYSTEM_ERROR);
        }
    }

    public List<Section> querySectionList(Integer page, Integer size) {
        SelectStatementProvider selectStatementProvider = select(sectionMapper.selectList)
                .from(SectionDynamicSqlSupport.section)
                .build().render(RenderingStrategies.MYBATIS3);
        List<Section> sections = sectionMapper.selectMany(selectStatementProvider);
        for (Section section : sections) {
            SelectStatementProvider selectStatementProvider1 = select(sum(NewsDynamicSqlSupport.news.newsViews),count(NewsDynamicSqlSupport.news.newsId))
                    .from(NewsDynamicSqlSupport.news)
                    .where(NewsDynamicSqlSupport.sectionId,isEqualTo(section.getSectionId()))
                    .and(NewsDynamicSqlSupport.publishStatus,isEqualTo(CommonConstant.NEWS_ISSUE))
                    .build().render(RenderingStrategies.MYBATIS3);

        }
        return sections;
    }
}
