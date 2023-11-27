package com.news.NS.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SectionDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source Table: section")
    public static final Section section = new Section();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source field: section.section_id")
    public static final SqlColumn<Integer> sectionId = section.sectionId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source field: section.section_name")
    public static final SqlColumn<String> sectionName = section.sectionName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source Table: section")
    public static final class Section extends SqlTable {
        public final SqlColumn<Integer> sectionId = column("section_id", JDBCType.INTEGER);

        public final SqlColumn<String> sectionName = column("section_name", JDBCType.CHAR);

        public Section() {
            super("section");
        }
    }
}