package com.news.NS.domain;

import lombok.ToString;

import javax.annotation.Generated;

@ToString
public class Section {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source field: section.section_id")
    private Integer sectionId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source field: section.section_name")
    private String sectionName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source field: section.section_id")
    public Integer getSectionId() {
        return sectionId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source field: section.section_id")
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source field: section.section_name")
    public String getSectionName() {
        return sectionName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source field: section.section_name")
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName == null ? null : sectionName.trim();
    }
}