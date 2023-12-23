package com.news.NS.mapper;

import static com.news.NS.mapper.FocusDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.news.NS.domain.Focus;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
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
public interface FocusMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2500366+08:00", comments="Source Table: user_focus")
    BasicColumn[] selectList = BasicColumn.columnList(userId, focusedUserId, focusTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2420592+08:00", comments="Source Table: user_focus")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2430534+08:00", comments="Source Table: user_focus")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2430534+08:00", comments="Source Table: user_focus")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Focus> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2440521+08:00", comments="Source Table: user_focus")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Focus> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2440521+08:00", comments="Source Table: user_focus")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("FocusResult")
    Optional<Focus> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2450498+08:00", comments="Source Table: user_focus")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="FocusResult", value = {
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="focused_user_id", property="focusedUserId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="focus_time", property="focusTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Focus> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2460464+08:00", comments="Source Table: user_focus")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2460464+08:00", comments="Source Table: user_focus")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, focus, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2460464+08:00", comments="Source Table: user_focus")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, focus, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2470437+08:00", comments="Source Table: user_focus")
    default int deleteByPrimaryKey(Integer userId_, Integer focusedUserId_) {
        return delete(c -> 
            c.where(userId, isEqualTo(userId_))
            .and(focusedUserId, isEqualTo(focusedUserId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2470437+08:00", comments="Source Table: user_focus")
    default int insert(Focus record) {
        return MyBatis3Utils.insert(this::insert, record, focus, c ->
            c.map(userId).toProperty("userId")
            .map(focusedUserId).toProperty("focusedUserId")
            .map(focusTime).toProperty("focusTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2480415+08:00", comments="Source Table: user_focus")
    default int insertMultiple(Collection<Focus> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, focus, c ->
            c.map(userId).toProperty("userId")
            .map(focusedUserId).toProperty("focusedUserId")
            .map(focusTime).toProperty("focusTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2490386+08:00", comments="Source Table: user_focus")
    default int insertSelective(Focus record) {
        return MyBatis3Utils.insert(this::insert, record, focus, c ->
            c.map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(focusedUserId).toPropertyWhenPresent("focusedUserId", record::getFocusedUserId)
            .map(focusTime).toPropertyWhenPresent("focusTime", record::getFocusTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2510334+08:00", comments="Source Table: user_focus")
    default Optional<Focus> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, focus, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2520304+08:00", comments="Source Table: user_focus")
    default List<Focus> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, focus, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2520304+08:00", comments="Source Table: user_focus")
    default List<Focus> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, focus, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2530276+08:00", comments="Source Table: user_focus")
    default Optional<Focus> selectByPrimaryKey(Integer userId_, Integer focusedUserId_) {
        return selectOne(c ->
            c.where(userId, isEqualTo(userId_))
            .and(focusedUserId, isEqualTo(focusedUserId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2540252+08:00", comments="Source Table: user_focus")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, focus, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2540252+08:00", comments="Source Table: user_focus")
    static UpdateDSL<UpdateModel> updateAllColumns(Focus record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId)
                .set(focusedUserId).equalTo(record::getFocusedUserId)
                .set(focusTime).equalTo(record::getFocusTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2540252+08:00", comments="Source Table: user_focus")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Focus record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId)
                .set(focusedUserId).equalToWhenPresent(record::getFocusedUserId)
                .set(focusTime).equalToWhenPresent(record::getFocusTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2550222+08:00", comments="Source Table: user_focus")
    default int updateByPrimaryKey(Focus record) {
        return update(c ->
            c.set(focusTime).equalTo(record::getFocusTime)
            .where(userId, isEqualTo(record::getUserId))
            .and(focusedUserId, isEqualTo(record::getFocusedUserId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2560198+08:00", comments="Source Table: user_focus")
    default int updateByPrimaryKeySelective(Focus record) {
        return update(c ->
            c.set(focusTime).equalToWhenPresent(record::getFocusTime)
            .where(userId, isEqualTo(record::getUserId))
            .and(focusedUserId, isEqualTo(record::getFocusedUserId))
        );
    }
}