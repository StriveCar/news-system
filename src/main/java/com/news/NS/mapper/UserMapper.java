package com.news.NS.mapper;

import static com.news.NS.mapper.UserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.news.NS.domain.User;
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
public interface UserMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    BasicColumn[] selectList = BasicColumn.columnList(userId, account, password, username, avatarUrl, phoneNumber, identification, createTime, lastUpdateTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<User> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<User> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserResult")
    Optional<User> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserResult", value = {
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="account", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar_url", property="avatarUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone_number", property="phoneNumber", jdbcType=JdbcType.CHAR),
        @Result(column="identification", property="identification", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_update_time", property="lastUpdateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, user, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, user, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default int deleteByPrimaryKey(Integer userId_) {
        return delete(c -> 
            c.where(userId, isEqualTo(userId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default int insert(User record) {
        return MyBatis3Utils.insert(this::insert, record, user, c ->
            c.map(userId).toProperty("userId")
            .map(account).toProperty("account")
            .map(password).toProperty("password")
            .map(username).toProperty("username")
            .map(avatarUrl).toProperty("avatarUrl")
            .map(phoneNumber).toProperty("phoneNumber")
            .map(identification).toProperty("identification")
            .map(createTime).toProperty("createTime")
            .map(lastUpdateTime).toProperty("lastUpdateTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default int insertMultiple(Collection<User> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, user, c ->
            c.map(userId).toProperty("userId")
            .map(account).toProperty("account")
            .map(password).toProperty("password")
            .map(username).toProperty("username")
            .map(avatarUrl).toProperty("avatarUrl")
            .map(phoneNumber).toProperty("phoneNumber")
            .map(identification).toProperty("identification")
            .map(createTime).toProperty("createTime")
            .map(lastUpdateTime).toProperty("lastUpdateTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default int insertSelective(User record) {
        return MyBatis3Utils.insert(this::insert, record, user, c ->
            c.map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(account).toPropertyWhenPresent("account", record::getAccount)
            .map(password).toPropertyWhenPresent("password", record::getPassword)
            .map(username).toPropertyWhenPresent("username", record::getUsername)
            .map(avatarUrl).toPropertyWhenPresent("avatarUrl", record::getAvatarUrl)
            .map(phoneNumber).toPropertyWhenPresent("phoneNumber", record::getPhoneNumber)
            .map(identification).toPropertyWhenPresent("identification", record::getIdentification)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(lastUpdateTime).toPropertyWhenPresent("lastUpdateTime", record::getLastUpdateTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default Optional<User> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, user, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default List<User> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, user, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default List<User> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, user, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default Optional<User> selectByPrimaryKey(Integer userId_) {
        return selectOne(c ->
            c.where(userId, isEqualTo(userId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, user, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    static UpdateDSL<UpdateModel> updateAllColumns(User record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId)
                .set(account).equalTo(record::getAccount)
                .set(password).equalTo(record::getPassword)
                .set(username).equalTo(record::getUsername)
                .set(avatarUrl).equalTo(record::getAvatarUrl)
                .set(phoneNumber).equalTo(record::getPhoneNumber)
                .set(identification).equalTo(record::getIdentification)
                .set(createTime).equalTo(record::getCreateTime)
                .set(lastUpdateTime).equalTo(record::getLastUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(User record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId)
                .set(account).equalToWhenPresent(record::getAccount)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(avatarUrl).equalToWhenPresent(record::getAvatarUrl)
                .set(phoneNumber).equalToWhenPresent(record::getPhoneNumber)
                .set(identification).equalToWhenPresent(record::getIdentification)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(lastUpdateTime).equalToWhenPresent(record::getLastUpdateTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default int updateByPrimaryKey(User record) {
        return update(c ->
            c.set(account).equalTo(record::getAccount)
            .set(password).equalTo(record::getPassword)
            .set(username).equalTo(record::getUsername)
            .set(avatarUrl).equalTo(record::getAvatarUrl)
            .set(phoneNumber).equalTo(record::getPhoneNumber)
            .set(identification).equalTo(record::getIdentification)
            .set(createTime).equalTo(record::getCreateTime)
            .set(lastUpdateTime).equalTo(record::getLastUpdateTime)
            .where(userId, isEqualTo(record::getUserId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.533+08:00", comments="Source Table: user_info")
    default int updateByPrimaryKeySelective(User record) {
        return update(c ->
            c.set(account).equalToWhenPresent(record::getAccount)
            .set(password).equalToWhenPresent(record::getPassword)
            .set(username).equalToWhenPresent(record::getUsername)
            .set(avatarUrl).equalToWhenPresent(record::getAvatarUrl)
            .set(phoneNumber).equalToWhenPresent(record::getPhoneNumber)
            .set(identification).equalToWhenPresent(record::getIdentification)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(lastUpdateTime).equalToWhenPresent(record::getLastUpdateTime)
            .where(userId, isEqualTo(record::getUserId))
        );
    }
}