package com.news.NS.mapper;

import static com.news.NS.mapper.ComplaintDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.news.NS.domain.Complaint;
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
public interface ComplaintMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    BasicColumn[] selectList = BasicColumn.columnList(complainerId, newsId, complaintTime, complaintReason);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Complaint> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Complaint> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ComplaintResult")
    Optional<Complaint> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ComplaintResult", value = {
        @Result(column="complainer_id", property="complainerId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="news_id", property="newsId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="complaint_time", property="complaintTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="complaint_reason", property="complaintReason", jdbcType=JdbcType.VARCHAR)
    })
    List<Complaint> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, complaint, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, complaint, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    default int deleteByPrimaryKey(Integer complainerId_, Integer newsId_) {
        return delete(c -> 
            c.where(complainerId, isEqualTo(complainerId_))
            .and(newsId, isEqualTo(newsId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    default int insert(Complaint record) {
        return MyBatis3Utils.insert(this::insert, record, complaint, c ->
            c.map(complainerId).toProperty("complainerId")
            .map(newsId).toProperty("newsId")
            .map(complaintTime).toProperty("complaintTime")
            .map(complaintReason).toProperty("complaintReason")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    default int insertMultiple(Collection<Complaint> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, complaint, c ->
            c.map(complainerId).toProperty("complainerId")
            .map(newsId).toProperty("newsId")
            .map(complaintTime).toProperty("complaintTime")
            .map(complaintReason).toProperty("complaintReason")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    default int insertSelective(Complaint record) {
        return MyBatis3Utils.insert(this::insert, record, complaint, c ->
            c.map(complainerId).toPropertyWhenPresent("complainerId", record::getComplainerId)
            .map(newsId).toPropertyWhenPresent("newsId", record::getNewsId)
            .map(complaintTime).toPropertyWhenPresent("complaintTime", record::getComplaintTime)
            .map(complaintReason).toPropertyWhenPresent("complaintReason", record::getComplaintReason)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    default Optional<Complaint> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, complaint, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    default List<Complaint> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, complaint, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9703104+08:00", comments="Source Table: news_complaint")
    default List<Complaint> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, complaint, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source Table: news_complaint")
    default Optional<Complaint> selectByPrimaryKey(Integer complainerId_, Integer newsId_) {
        return selectOne(c ->
            c.where(complainerId, isEqualTo(complainerId_))
            .and(newsId, isEqualTo(newsId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source Table: news_complaint")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, complaint, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source Table: news_complaint")
    static UpdateDSL<UpdateModel> updateAllColumns(Complaint record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(complainerId).equalTo(record::getComplainerId)
                .set(newsId).equalTo(record::getNewsId)
                .set(complaintTime).equalTo(record::getComplaintTime)
                .set(complaintReason).equalTo(record::getComplaintReason);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source Table: news_complaint")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Complaint record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(complainerId).equalToWhenPresent(record::getComplainerId)
                .set(newsId).equalToWhenPresent(record::getNewsId)
                .set(complaintTime).equalToWhenPresent(record::getComplaintTime)
                .set(complaintReason).equalToWhenPresent(record::getComplaintReason);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source Table: news_complaint")
    default int updateByPrimaryKey(Complaint record) {
        return update(c ->
            c.set(complaintTime).equalTo(record::getComplaintTime)
            .set(complaintReason).equalTo(record::getComplaintReason)
            .where(complainerId, isEqualTo(record::getComplainerId))
            .and(newsId, isEqualTo(record::getNewsId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-11-25T17:58:01.9713077+08:00", comments="Source Table: news_complaint")
    default int updateByPrimaryKeySelective(Complaint record) {
        return update(c ->
            c.set(complaintTime).equalToWhenPresent(record::getComplaintTime)
            .set(complaintReason).equalToWhenPresent(record::getComplaintReason)
            .where(complainerId, isEqualTo(record::getComplainerId))
            .and(newsId, isEqualTo(record::getNewsId))
        );
    }
}