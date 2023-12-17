package com.news.NS.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.NS.common.AlertException;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.domain.News;
import com.news.NS.domain.Section;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.vo.SectionNewsVo;
import com.news.NS.mapper.NewsDynamicSqlSupport;
import com.news.NS.mapper.NewsMapper;
import com.news.NS.mapper.SectionDynamicSqlSupport;
import com.news.NS.mapper.SectionMapper;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private NewsMapper newsMapper;

    public Section addSection(String sectionName) {
        if (sectionMapper.count(c -> c.where(SectionDynamicSqlSupport.sectionName, isEqualTo(sectionName))) != 0) {
            throw new AlertException(ResultCode.SECTION_EXIST);
        }
        Section section = new Section();
        section.setSectionName(sectionName);

        if (sectionMapper.insert(section) == 0) {
            throw new AlertException(ResultCode.SYSTEM_ERROR);
        }

        section.setSectionId(sectionMapper.selectOne(c ->
                        c.where(SectionDynamicSqlSupport.sectionName, isEqualTo(sectionName)))
                .get().getSectionId());
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

    public PageInfo<Section> querySectionList(Integer page, Integer size) {
        SelectStatementProvider selectStatementProvider = select(sectionMapper.selectList)
                .from(SectionDynamicSqlSupport.section)
                .build().render(RenderingStrategies.MYBATIS3);
        Page<Section> queryPageData = PageHelper.startPage(page, size);
        List<Section> sections = sectionMapper.selectMany(selectStatementProvider);

        if (sections.isEmpty()) {
            throw new AlertException(ResultCode.SECTION_LIST_NOT_EXIST);
        }
        PageInfo<Section> pageInfo = new PageInfo<>();
        pageInfo.setPageData(sectionMapper.selectMany(selectStatementProvider));
        pageInfo.setPage(page);
        pageInfo.setTotalSize(queryPageData.getTotal());
        System.out.println(sections);
        return pageInfo;
    }

    public PageInfo<SectionNewsVo> querySectionDataList(Integer page, Integer size) {
        SelectStatementProvider selectStatementProvider = select(sectionMapper.selectList)
                .from(SectionDynamicSqlSupport.section)
                .build().render(RenderingStrategies.MYBATIS3);

        Page<Section> queryPageData = PageHelper.startPage(page, size);
        List<Section> sections = sectionMapper.selectMany(selectStatementProvider);
        List<SectionNewsVo> sectionNewsVos = new ArrayList<>();
        BeanCopier sectionCopier = BeanCopier.create(Section.class, SectionNewsVo.class, false);
        for (Section section : sections) {
            SectionNewsVo sectionNewsVo = newsMapper.selectSectionData(section.getSectionId());
            if (sectionNewsVo.getViewsSum() == null)sectionNewsVo.setViewsSum(0);
            if (sectionNewsVo.getLikeSum() == null)sectionNewsVo.setLikeSum(0);
            sectionCopier.copy(section,sectionNewsVo,null);
            sectionNewsVos.add(sectionNewsVo);
        }
        PageInfo<SectionNewsVo> pageInfo = new PageInfo<>();
        pageInfo.setPage(page);
        pageInfo.setTotalSize(queryPageData.getTotal());
        pageInfo.setPageData(sectionNewsVos);
        return pageInfo;
    }
}
