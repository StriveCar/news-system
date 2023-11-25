package com.news.NS.domain;

import javax.annotation.Generated;

public class Section {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source field: section.section_id")
    private Integer sectionId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source field: section.section_name")
    private String sectionName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source field: section.section_id")
    public Integer getSectionId() {
        return sectionId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source field: section.section_id")
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source field: section.section_name")
    public String getSectionName() {
        return sectionName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source field: section.section_name")
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName == null ? null : sectionName.trim();
    }
}