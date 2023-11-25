package com.news.NS.mapper;

import static com.news.NS.mapper.ApplicationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.news.NS.domain.Application;
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
public interface ApplicationMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9742999+08:00", comments="Source Table: news_publisher_application")
    BasicColumn[] selectList = BasicColumn.columnList(userId, applicationReason, applicationStatus, applicationTime, reviewTime, reviewRemark);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9723051+08:00", comments="Source Table: news_publisher_application")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9733026+08:00", comments="Source Table: news_publisher_application")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9733026+08:00", comments="Source Table: news_publisher_application")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Application> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9733026+08:00", comments="Source Table: news_publisher_application")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Application> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9733026+08:00", comments="Source Table: news_publisher_application")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ApplicationResult")
    Optional<Application> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9733026+08:00", comments="Source Table: news_publisher_application")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ApplicationResult", value = {
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="application_reason", property="applicationReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="application_status", property="applicationStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="application_time", property="applicationTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="review_time", property="reviewTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="review_remark", property="reviewRemark", jdbcType=JdbcType.VARCHAR)
    })
    List<Application> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9733026+08:00", comments="Source Table: news_publisher_application")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9733026+08:00", comments="Source Table: news_publisher_application")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, application, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9733026+08:00", comments="Source Table: news_publisher_application")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, application, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9733026+08:00", comments="Source Table: news_publisher_application")
    default int deleteByPrimaryKey(Integer userId_) {
        return delete(c -> 
            c.where(userId, isEqualTo(userId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9733026+08:00", comments="Source Table: news_publisher_application")
    default int insert(Application record) {
        return MyBatis3Utils.insert(this::insert, record, application, c ->
            c.map(userId).toProperty("userId")
            .map(applicationReason).toProperty("applicationReason")
            .map(applicationStatus).toProperty("applicationStatus")
            .map(applicationTime).toProperty("applicationTime")
            .map(reviewTime).toProperty("reviewTime")
            .map(reviewRemark).toProperty("reviewRemark")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9733026+08:00", comments="Source Table: news_publisher_application")
    default int insertMultiple(Collection<Application> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, application, c ->
            c.map(userId).toProperty("userId")
            .map(applicationReason).toProperty("applicationReason")
            .map(applicationStatus).toProperty("applicationStatus")
            .map(applicationTime).toProperty("applicationTime")
            .map(reviewTime).toProperty("reviewTime")
            .map(reviewRemark).toProperty("reviewRemark")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9733026+08:00", comments="Source Table: news_publisher_application")
    default int insertSelective(Application record) {
        return MyBatis3Utils.insert(this::insert, record, application, c ->
            c.map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(applicationReason).toPropertyWhenPresent("applicationReason", record::getApplicationReason)
            .map(applicationStatus).toPropertyWhenPresent("applicationStatus", record::getApplicationStatus)
            .map(applicationTime).toPropertyWhenPresent("applicationTime", record::getApplicationTime)
            .map(reviewTime).toPropertyWhenPresent("reviewTime", record::getReviewTime)
            .map(reviewRemark).toPropertyWhenPresent("reviewRemark", record::getReviewRemark)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9742999+08:00", comments="Source Table: news_publisher_application")
    default Optional<Application> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, application, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9742999+08:00", comments="Source Table: news_publisher_application")
    default List<Application> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, application, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9742999+08:00", comments="Source Table: news_publisher_application")
    default List<Application> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, application, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9742999+08:00", comments="Source Table: news_publisher_application")
    default Optional<Application> selectByPrimaryKey(Integer userId_) {
        return selectOne(c ->
            c.where(userId, isEqualTo(userId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9742999+08:00", comments="Source Table: news_publisher_application")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, application, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9742999+08:00", comments="Source Table: news_publisher_application")
    static UpdateDSL<UpdateModel> updateAllColumns(Application record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId)
                .set(applicationReason).equalTo(record::getApplicationReason)
                .set(applicationStatus).equalTo(record::getApplicationStatus)
                .set(applicationTime).equalTo(record::getApplicationTime)
                .set(reviewTime).equalTo(record::getReviewTime)
                .set(reviewRemark).equalTo(record::getReviewRemark);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9742999+08:00", comments="Source Table: news_publisher_application")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Application record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId)
                .set(applicationReason).equalToWhenPresent(record::getApplicationReason)
                .set(applicationStatus).equalToWhenPresent(record::getApplicationStatus)
                .set(applicationTime).equalToWhenPresent(record::getApplicationTime)
                .set(reviewTime).equalToWhenPresent(record::getReviewTime)
                .set(reviewRemark).equalToWhenPresent(record::getReviewRemark);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9742999+08:00", comments="Source Table: news_publisher_application")
    default int updateByPrimaryKey(Application record) {
        return update(c ->
            c.set(applicationReason).equalTo(record::getApplicationReason)
            .set(applicationStatus).equalTo(record::getApplicationStatus)
            .set(applicationTime).equalTo(record::getApplicationTime)
            .set(reviewTime).equalTo(record::getReviewTime)
            .set(reviewRemark).equalTo(record::getReviewRemark)
            .where(userId, isEqualTo(record::getUserId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9742999+08:00", comments="Source Table: news_publisher_application")
    default int updateByPrimaryKeySelective(Application record) {
        return update(c ->
            c.set(applicationReason).equalToWhenPresent(record::getApplicationReason)
            .set(applicationStatus).equalToWhenPresent(record::getApplicationStatus)
            .set(applicationTime).equalToWhenPresent(record::getApplicationTime)
            .set(reviewTime).equalToWhenPresent(record::getReviewTime)
            .set(reviewRemark).equalToWhenPresent(record::getReviewRemark)
            .where(userId, isEqualTo(record::getUserId))
        );
    }
}