package com.news.NS.mapper;

import static com.news.NS.mapper.NewsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.news.NS.domain.News;
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
public interface NewsMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source Table: news")
    BasicColumn[] selectList = BasicColumn.columnList(newsId, publisherId, sectionId, title, content, newsViews, publishTime, publishStatus);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.53+08:00", comments="Source Table: news")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.53+08:00", comments="Source Table: news")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.53+08:00", comments="Source Table: news")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<News> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.53+08:00", comments="Source Table: news")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<News> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.53+08:00", comments="Source Table: news")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("NewsResult")
    Optional<News> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.53+08:00", comments="Source Table: news")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="NewsResult", value = {
        @Result(column="news_id", property="newsId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="publisher_id", property="publisherId", jdbcType=JdbcType.INTEGER),
        @Result(column="section_id", property="sectionId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="news_views", property="newsViews", jdbcType=JdbcType.INTEGER),
        @Result(column="publish_time", property="publishTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="publish_status", property="publishStatus", jdbcType=JdbcType.TINYINT)
    })
    List<News> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.53+08:00", comments="Source Table: news")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.53+08:00", comments="Source Table: news")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, news, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.53+08:00", comments="Source Table: news")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, news, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.53+08:00", comments="Source Table: news")
    default int deleteByPrimaryKey(Integer newsId_) {
        return delete(c -> 
            c.where(newsId, isEqualTo(newsId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.53+08:00", comments="Source Table: news")
    default int insert(News record) {
        return MyBatis3Utils.insert(this::insert, record, news, c ->
            c.map(newsId).toProperty("newsId")
            .map(publisherId).toProperty("publisherId")
            .map(sectionId).toProperty("sectionId")
            .map(title).toProperty("title")
            .map(content).toProperty("content")
            .map(newsViews).toProperty("newsViews")
            .map(publishTime).toProperty("publishTime")
            .map(publishStatus).toProperty("publishStatus")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.53+08:00", comments="Source Table: news")
    default int insertMultiple(Collection<News> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, news, c ->
            c.map(newsId).toProperty("newsId")
            .map(publisherId).toProperty("publisherId")
            .map(sectionId).toProperty("sectionId")
            .map(title).toProperty("title")
            .map(content).toProperty("content")
            .map(newsViews).toProperty("newsViews")
            .map(publishTime).toProperty("publishTime")
            .map(publishStatus).toProperty("publishStatus")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source Table: news")
    default int insertSelective(News record) {
        return MyBatis3Utils.insert(this::insert, record, news, c ->
            c.map(newsId).toPropertyWhenPresent("newsId", record::getNewsId)
            .map(publisherId).toPropertyWhenPresent("publisherId", record::getPublisherId)
            .map(sectionId).toPropertyWhenPresent("sectionId", record::getSectionId)
            .map(title).toPropertyWhenPresent("title", record::getTitle)
            .map(content).toPropertyWhenPresent("content", record::getContent)
            .map(newsViews).toPropertyWhenPresent("newsViews", record::getNewsViews)
            .map(publishTime).toPropertyWhenPresent("publishTime", record::getPublishTime)
            .map(publishStatus).toPropertyWhenPresent("publishStatus", record::getPublishStatus)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source Table: news")
    default Optional<News> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, news, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source Table: news")
    default List<News> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, news, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source Table: news")
    default List<News> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, news, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source Table: news")
    default Optional<News> selectByPrimaryKey(Integer newsId_) {
        return selectOne(c ->
            c.where(newsId, isEqualTo(newsId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source Table: news")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, news, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source Table: news")
    static UpdateDSL<UpdateModel> updateAllColumns(News record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(newsId).equalTo(record::getNewsId)
                .set(publisherId).equalTo(record::getPublisherId)
                .set(sectionId).equalTo(record::getSectionId)
                .set(title).equalTo(record::getTitle)
                .set(content).equalTo(record::getContent)
                .set(newsViews).equalTo(record::getNewsViews)
                .set(publishTime).equalTo(record::getPublishTime)
                .set(publishStatus).equalTo(record::getPublishStatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source Table: news")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(News record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(newsId).equalToWhenPresent(record::getNewsId)
                .set(publisherId).equalToWhenPresent(record::getPublisherId)
                .set(sectionId).equalToWhenPresent(record::getSectionId)
                .set(title).equalToWhenPresent(record::getTitle)
                .set(content).equalToWhenPresent(record::getContent)
                .set(newsViews).equalToWhenPresent(record::getNewsViews)
                .set(publishTime).equalToWhenPresent(record::getPublishTime)
                .set(publishStatus).equalToWhenPresent(record::getPublishStatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source Table: news")
    default int updateByPrimaryKey(News record) {
        return update(c ->
            c.set(publisherId).equalTo(record::getPublisherId)
            .set(sectionId).equalTo(record::getSectionId)
            .set(title).equalTo(record::getTitle)
            .set(content).equalTo(record::getContent)
            .set(newsViews).equalTo(record::getNewsViews)
            .set(publishTime).equalTo(record::getPublishTime)
            .set(publishStatus).equalTo(record::getPublishStatus)
            .where(newsId, isEqualTo(record::getNewsId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.531+08:00", comments="Source Table: news")
    default int updateByPrimaryKeySelective(News record) {
        return update(c ->
            c.set(publisherId).equalToWhenPresent(record::getPublisherId)
            .set(sectionId).equalToWhenPresent(record::getSectionId)
            .set(title).equalToWhenPresent(record::getTitle)
            .set(content).equalToWhenPresent(record::getContent)
            .set(newsViews).equalToWhenPresent(record::getNewsViews)
            .set(publishTime).equalToWhenPresent(record::getPublishTime)
            .set(publishStatus).equalToWhenPresent(record::getPublishStatus)
            .where(newsId, isEqualTo(record::getNewsId))
        );
    }
}