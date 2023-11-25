package com.news.NS.mapper;

import static com.news.NS.mapper.NewsReviewDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.news.NS.domain.NewsReview;
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
public interface NewsReviewMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    BasicColumn[] selectList = BasicColumn.columnList(reviewerId, newsId, reviewStatus, reviewTime, remark);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<NewsReview> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<NewsReview> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("NewsReviewResult")
    Optional<NewsReview> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="NewsReviewResult", value = {
        @Result(column="reviewer_id", property="reviewerId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="news_id", property="newsId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="review_status", property="reviewStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="review_time", property="reviewTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<NewsReview> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, newsReview, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, newsReview, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    default int deleteByPrimaryKey(Integer reviewerId_, Integer newsId_) {
        return delete(c -> 
            c.where(reviewerId, isEqualTo(reviewerId_))
            .and(newsId, isEqualTo(newsId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    default int insert(NewsReview record) {
        return MyBatis3Utils.insert(this::insert, record, newsReview, c ->
            c.map(reviewerId).toProperty("reviewerId")
            .map(newsId).toProperty("newsId")
            .map(reviewStatus).toProperty("reviewStatus")
            .map(reviewTime).toProperty("reviewTime")
            .map(remark).toProperty("remark")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    default int insertMultiple(Collection<NewsReview> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, newsReview, c ->
            c.map(reviewerId).toProperty("reviewerId")
            .map(newsId).toProperty("newsId")
            .map(reviewStatus).toProperty("reviewStatus")
            .map(reviewTime).toProperty("reviewTime")
            .map(remark).toProperty("remark")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    default int insertSelective(NewsReview record) {
        return MyBatis3Utils.insert(this::insert, record, newsReview, c ->
            c.map(reviewerId).toPropertyWhenPresent("reviewerId", record::getReviewerId)
            .map(newsId).toPropertyWhenPresent("newsId", record::getNewsId)
            .map(reviewStatus).toPropertyWhenPresent("reviewStatus", record::getReviewStatus)
            .map(reviewTime).toPropertyWhenPresent("reviewTime", record::getReviewTime)
            .map(remark).toPropertyWhenPresent("remark", record::getRemark)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9792865+08:00", comments="Source Table: news_review")
    default Optional<NewsReview> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, newsReview, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source Table: news_review")
    default List<NewsReview> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, newsReview, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source Table: news_review")
    default List<NewsReview> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, newsReview, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source Table: news_review")
    default Optional<NewsReview> selectByPrimaryKey(Integer reviewerId_, Integer newsId_) {
        return selectOne(c ->
            c.where(reviewerId, isEqualTo(reviewerId_))
            .and(newsId, isEqualTo(newsId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source Table: news_review")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, newsReview, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source Table: news_review")
    static UpdateDSL<UpdateModel> updateAllColumns(NewsReview record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(reviewerId).equalTo(record::getReviewerId)
                .set(newsId).equalTo(record::getNewsId)
                .set(reviewStatus).equalTo(record::getReviewStatus)
                .set(reviewTime).equalTo(record::getReviewTime)
                .set(remark).equalTo(record::getRemark);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source Table: news_review")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(NewsReview record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(reviewerId).equalToWhenPresent(record::getReviewerId)
                .set(newsId).equalToWhenPresent(record::getNewsId)
                .set(reviewStatus).equalToWhenPresent(record::getReviewStatus)
                .set(reviewTime).equalToWhenPresent(record::getReviewTime)
                .set(remark).equalToWhenPresent(record::getRemark);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source Table: news_review")
    default int updateByPrimaryKey(NewsReview record) {
        return update(c ->
            c.set(reviewStatus).equalTo(record::getReviewStatus)
            .set(reviewTime).equalTo(record::getReviewTime)
            .set(remark).equalTo(record::getRemark)
            .where(reviewerId, isEqualTo(record::getReviewerId))
            .and(newsId, isEqualTo(record::getNewsId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9802837+08:00", comments="Source Table: news_review")
    default int updateByPrimaryKeySelective(NewsReview record) {
        return update(c ->
            c.set(reviewStatus).equalToWhenPresent(record::getReviewStatus)
            .set(reviewTime).equalToWhenPresent(record::getReviewTime)
            .set(remark).equalToWhenPresent(record::getRemark)
            .where(reviewerId, isEqualTo(record::getReviewerId))
            .and(newsId, isEqualTo(record::getNewsId))
        );
    }
}