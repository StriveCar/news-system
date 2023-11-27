package com.news.NS.mapper;

import static com.news.NS.mapper.SecondCommentDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.news.NS.domain.SecondComment;
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
public interface SecondCommentMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.526+08:00", comments="Source Table: second_level_comment")
    BasicColumn[] selectList = BasicColumn.columnList(commentId, parentCommentId, publisherId, ikeNumber, content, publishTime);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.525+08:00", comments="Source Table: second_level_comment")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.525+08:00", comments="Source Table: second_level_comment")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.525+08:00", comments="Source Table: second_level_comment")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<SecondComment> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.525+08:00", comments="Source Table: second_level_comment")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<SecondComment> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.525+08:00", comments="Source Table: second_level_comment")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SecondCommentResult")
    Optional<SecondComment> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.525+08:00", comments="Source Table: second_level_comment")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SecondCommentResult", value = {
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="parent_comment_id", property="parentCommentId", jdbcType=JdbcType.INTEGER),
        @Result(column="publisher_id", property="publisherId", jdbcType=JdbcType.INTEGER),
        @Result(column="ike_number", property="ikeNumber", jdbcType=JdbcType.INTEGER),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="publish_time", property="publishTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SecondComment> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.525+08:00", comments="Source Table: second_level_comment")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.525+08:00", comments="Source Table: second_level_comment")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, secondComment, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.525+08:00", comments="Source Table: second_level_comment")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, secondComment, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.525+08:00", comments="Source Table: second_level_comment")
    default int deleteByPrimaryKey(Integer commentId_) {
        return delete(c -> 
            c.where(commentId, isEqualTo(commentId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.525+08:00", comments="Source Table: second_level_comment")
    default int insert(SecondComment record) {
        return MyBatis3Utils.insert(this::insert, record, secondComment, c ->
            c.map(commentId).toProperty("commentId")
            .map(parentCommentId).toProperty("parentCommentId")
            .map(publisherId).toProperty("publisherId")
            .map(ikeNumber).toProperty("ikeNumber")
            .map(content).toProperty("content")
            .map(publishTime).toProperty("publishTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.526+08:00", comments="Source Table: second_level_comment")
    default int insertMultiple(Collection<SecondComment> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, secondComment, c ->
            c.map(commentId).toProperty("commentId")
            .map(parentCommentId).toProperty("parentCommentId")
            .map(publisherId).toProperty("publisherId")
            .map(ikeNumber).toProperty("ikeNumber")
            .map(content).toProperty("content")
            .map(publishTime).toProperty("publishTime")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.526+08:00", comments="Source Table: second_level_comment")
    default int insertSelective(SecondComment record) {
        return MyBatis3Utils.insert(this::insert, record, secondComment, c ->
            c.map(commentId).toPropertyWhenPresent("commentId", record::getCommentId)
            .map(parentCommentId).toPropertyWhenPresent("parentCommentId", record::getParentCommentId)
            .map(publisherId).toPropertyWhenPresent("publisherId", record::getPublisherId)
            .map(ikeNumber).toPropertyWhenPresent("ikeNumber", record::getIkeNumber)
            .map(content).toPropertyWhenPresent("content", record::getContent)
            .map(publishTime).toPropertyWhenPresent("publishTime", record::getPublishTime)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.526+08:00", comments="Source Table: second_level_comment")
    default Optional<SecondComment> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, secondComment, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.526+08:00", comments="Source Table: second_level_comment")
    default List<SecondComment> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, secondComment, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.526+08:00", comments="Source Table: second_level_comment")
    default List<SecondComment> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, secondComment, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.526+08:00", comments="Source Table: second_level_comment")
    default Optional<SecondComment> selectByPrimaryKey(Integer commentId_) {
        return selectOne(c ->
            c.where(commentId, isEqualTo(commentId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.526+08:00", comments="Source Table: second_level_comment")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, secondComment, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.526+08:00", comments="Source Table: second_level_comment")
    static UpdateDSL<UpdateModel> updateAllColumns(SecondComment record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(commentId).equalTo(record::getCommentId)
                .set(parentCommentId).equalTo(record::getParentCommentId)
                .set(publisherId).equalTo(record::getPublisherId)
                .set(ikeNumber).equalTo(record::getIkeNumber)
                .set(content).equalTo(record::getContent)
                .set(publishTime).equalTo(record::getPublishTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.526+08:00", comments="Source Table: second_level_comment")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SecondComment record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(commentId).equalToWhenPresent(record::getCommentId)
                .set(parentCommentId).equalToWhenPresent(record::getParentCommentId)
                .set(publisherId).equalToWhenPresent(record::getPublisherId)
                .set(ikeNumber).equalToWhenPresent(record::getIkeNumber)
                .set(content).equalToWhenPresent(record::getContent)
                .set(publishTime).equalToWhenPresent(record::getPublishTime);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.526+08:00", comments="Source Table: second_level_comment")
    default int updateByPrimaryKey(SecondComment record) {
        return update(c ->
            c.set(parentCommentId).equalTo(record::getParentCommentId)
            .set(publisherId).equalTo(record::getPublisherId)
            .set(ikeNumber).equalTo(record::getIkeNumber)
            .set(content).equalTo(record::getContent)
            .set(publishTime).equalTo(record::getPublishTime)
            .where(commentId, isEqualTo(record::getCommentId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-26T23:08:08.526+08:00", comments="Source Table: second_level_comment")
    default int updateByPrimaryKeySelective(SecondComment record) {
        return update(c ->
            c.set(parentCommentId).equalToWhenPresent(record::getParentCommentId)
            .set(publisherId).equalToWhenPresent(record::getPublisherId)
            .set(ikeNumber).equalToWhenPresent(record::getIkeNumber)
            .set(content).equalToWhenPresent(record::getContent)
            .set(publishTime).equalToWhenPresent(record::getPublishTime)
            .where(commentId, isEqualTo(record::getCommentId))
        );
    }
}