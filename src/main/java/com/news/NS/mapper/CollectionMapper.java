package com.news.NS.mapper;

import static com.news.NS.mapper.CollectionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.news.NS.domain.Collection;
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
public interface CollectionMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    BasicColumn[] selectList = BasicColumn.columnList(userId, newsId, collectionTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Collection> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Collection> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CollectionResult")
    Optional<Collection> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CollectionResult", value = {
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="news_id", property="newsId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="collection_time", property="collectionTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Collection> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, collection, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, collection, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    default int deleteByPrimaryKey(Integer userId_, Integer newsId_) {
        return delete(c -> 
            c.where(userId, isEqualTo(userId_))
            .and(newsId, isEqualTo(newsId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    default int insert(Collection record) {
        return MyBatis3Utils.insert(this::insert, record, collection, c ->
            c.map(userId).toProperty("userId")
            .map(newsId).toProperty("newsId")
            .map(collectionTime).toProperty("collectionTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    default int insertMultiple(Collection<Collection> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, collection, c ->
            c.map(userId).toProperty("userId")
            .map(newsId).toProperty("newsId")
            .map(collectionTime).toProperty("collectionTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9763142+08:00", comments="Source Table: news_collection")
    default int insertSelective(Collection record) {
        return MyBatis3Utils.insert(this::insert, record, collection, c ->
            c.map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(newsId).toPropertyWhenPresent("newsId", record::getNewsId)
            .map(collectionTime).toPropertyWhenPresent("collectionTime", record::getCollectionTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9773129+08:00", comments="Source Table: news_collection")
    default Optional<Collection> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, collection, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9773129+08:00", comments="Source Table: news_collection")
    default List<Collection> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, collection, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9773129+08:00", comments="Source Table: news_collection")
    default List<Collection> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, collection, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9773129+08:00", comments="Source Table: news_collection")
    default Optional<Collection> selectByPrimaryKey(Integer userId_, Integer newsId_) {
        return selectOne(c ->
            c.where(userId, isEqualTo(userId_))
            .and(newsId, isEqualTo(newsId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9773129+08:00", comments="Source Table: news_collection")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, collection, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9773129+08:00", comments="Source Table: news_collection")
    static UpdateDSL<UpdateModel> updateAllColumns(Collection record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId)
                .set(newsId).equalTo(record::getNewsId)
                .set(collectionTime).equalTo(record::getCollectionTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9773129+08:00", comments="Source Table: news_collection")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Collection record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId)
                .set(newsId).equalToWhenPresent(record::getNewsId)
                .set(collectionTime).equalToWhenPresent(record::getCollectionTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9773129+08:00", comments="Source Table: news_collection")
    default int updateByPrimaryKey(Collection record) {
        return update(c ->
            c.set(collectionTime).equalTo(record::getCollectionTime)
            .where(userId, isEqualTo(record::getUserId))
            .and(newsId, isEqualTo(record::getNewsId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9773129+08:00", comments="Source Table: news_collection")
    default int updateByPrimaryKeySelective(Collection record) {
        return update(c ->
            c.set(collectionTime).equalToWhenPresent(record::getCollectionTime)
            .where(userId, isEqualTo(record::getUserId))
            .and(newsId, isEqualTo(record::getNewsId))
        );
    }
}