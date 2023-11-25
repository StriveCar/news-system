package com.news.NS.mapper;

import static com.news.NS.mapper.NotificationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.news.NS.domain.Notification;
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
public interface NotificationMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9623314+08:00", comments="Source Table: notification")
    BasicColumn[] selectList = BasicColumn.columnList(notificationId, userId, content, createTime, hasRead, type);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9563833+08:00", comments="Source Table: notification")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9573465+08:00", comments="Source Table: notification")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9573465+08:00", comments="Source Table: notification")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Notification> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9573465+08:00", comments="Source Table: notification")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Notification> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9583619+08:00", comments="Source Table: notification")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("NotificationResult")
    Optional<Notification> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9583619+08:00", comments="Source Table: notification")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="NotificationResult", value = {
        @Result(column="notification_id", property="notificationId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="has_read", property="hasRead", jdbcType=JdbcType.TINYINT),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT)
    })
    List<Notification> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.95934+08:00", comments="Source Table: notification")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.95934+08:00", comments="Source Table: notification")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, notification, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9603371+08:00", comments="Source Table: notification")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, notification, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9603371+08:00", comments="Source Table: notification")
    default int deleteByPrimaryKey(Integer notificationId_) {
        return delete(c -> 
            c.where(notificationId, isEqualTo(notificationId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9603371+08:00", comments="Source Table: notification")
    default int insert(Notification record) {
        return MyBatis3Utils.insert(this::insert, record, notification, c ->
            c.map(notificationId).toProperty("notificationId")
            .map(userId).toProperty("userId")
            .map(content).toProperty("content")
            .map(createTime).toProperty("createTime")
            .map(hasRead).toProperty("hasRead")
            .map(type).toProperty("type")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9613344+08:00", comments="Source Table: notification")
    default int insertMultiple(Collection<Notification> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, notification, c ->
            c.map(notificationId).toProperty("notificationId")
            .map(userId).toProperty("userId")
            .map(content).toProperty("content")
            .map(createTime).toProperty("createTime")
            .map(hasRead).toProperty("hasRead")
            .map(type).toProperty("type")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9613344+08:00", comments="Source Table: notification")
    default int insertSelective(Notification record) {
        return MyBatis3Utils.insert(this::insert, record, notification, c ->
            c.map(notificationId).toPropertyWhenPresent("notificationId", record::getNotificationId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(content).toPropertyWhenPresent("content", record::getContent)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(hasRead).toPropertyWhenPresent("hasRead", record::getHasRead)
            .map(type).toPropertyWhenPresent("type", record::getType)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9633295+08:00", comments="Source Table: notification")
    default Optional<Notification> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, notification, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9633295+08:00", comments="Source Table: notification")
    default List<Notification> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, notification, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9643264+08:00", comments="Source Table: notification")
    default List<Notification> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, notification, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9643264+08:00", comments="Source Table: notification")
    default Optional<Notification> selectByPrimaryKey(Integer notificationId_) {
        return selectOne(c ->
            c.where(notificationId, isEqualTo(notificationId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9643264+08:00", comments="Source Table: notification")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, notification, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9653233+08:00", comments="Source Table: notification")
    static UpdateDSL<UpdateModel> updateAllColumns(Notification record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(notificationId).equalTo(record::getNotificationId)
                .set(userId).equalTo(record::getUserId)
                .set(content).equalTo(record::getContent)
                .set(createTime).equalTo(record::getCreateTime)
                .set(hasRead).equalTo(record::getHasRead)
                .set(type).equalTo(record::getType);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9653233+08:00", comments="Source Table: notification")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Notification record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(notificationId).equalToWhenPresent(record::getNotificationId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(content).equalToWhenPresent(record::getContent)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(hasRead).equalToWhenPresent(record::getHasRead)
                .set(type).equalToWhenPresent(record::getType);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9663228+08:00", comments="Source Table: notification")
    default int updateByPrimaryKey(Notification record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(content).equalTo(record::getContent)
            .set(createTime).equalTo(record::getCreateTime)
            .set(hasRead).equalTo(record::getHasRead)
            .set(type).equalTo(record::getType)
            .where(notificationId, isEqualTo(record::getNotificationId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9663228+08:00", comments="Source Table: notification")
    default int updateByPrimaryKeySelective(Notification record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(content).equalToWhenPresent(record::getContent)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(hasRead).equalToWhenPresent(record::getHasRead)
            .set(type).equalToWhenPresent(record::getType)
            .where(notificationId, isEqualTo(record::getNotificationId))
        );
    }
}