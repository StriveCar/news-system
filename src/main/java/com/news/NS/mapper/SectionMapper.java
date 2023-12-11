package com.news.NS.mapper;

import static com.news.NS.mapper.SectionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.news.NS.domain.Section;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

import com.news.NS.domain.vo.SectionNewsVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface SectionMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    BasicColumn[] selectList = BasicColumn.columnList(sectionId, sectionName);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source Table: section")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source Table: section")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.522+08:00", comments="Source Table: section")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Section> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Section> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SectionResult")
    Optional<Section> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SectionResult", value = {
        @Result(column="section_id", property="sectionId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="section_name", property="sectionName", jdbcType=JdbcType.CHAR)
    })
    List<Section> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, section, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, section, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default int deleteByPrimaryKey(Integer sectionId_) {
        return delete(c -> 
            c.where(sectionId, isEqualTo(sectionId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default int insert(Section record) {
        return MyBatis3Utils.insert(this::insert, record, section, c ->
            c.map(sectionId).toProperty("sectionId")
            .map(sectionName).toProperty("sectionName")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default int insertMultiple(Collection<Section> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, section, c ->
            c.map(sectionId).toProperty("sectionId")
            .map(sectionName).toProperty("sectionName")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default int insertSelective(Section record) {
        return MyBatis3Utils.insert(this::insert, record, section, c ->
            c.map(sectionId).toPropertyWhenPresent("sectionId", record::getSectionId)
            .map(sectionName).toPropertyWhenPresent("sectionName", record::getSectionName)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default Optional<Section> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, section, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default List<Section> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, section, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default List<Section> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, section, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default Optional<Section> selectByPrimaryKey(Integer sectionId_) {
        return selectOne(c ->
            c.where(sectionId, isEqualTo(sectionId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, section, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    static UpdateDSL<UpdateModel> updateAllColumns(Section record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(sectionId).equalTo(record::getSectionId)
                .set(sectionName).equalTo(record::getSectionName);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Section record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(sectionId).equalToWhenPresent(record::getSectionId)
                .set(sectionName).equalToWhenPresent(record::getSectionName);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default int updateByPrimaryKey(Section record) {
        return update(c ->
            c.set(sectionName).equalTo(record::getSectionName)
            .where(sectionId, isEqualTo(record::getSectionId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.524+08:00", comments="Source Table: section")
    default int updateByPrimaryKeySelective(Section record) {
        return update(c ->
            c.set(sectionName).equalToWhenPresent(record::getSectionName)
            .where(sectionId, isEqualTo(record::getSectionId))
        );
    }

}