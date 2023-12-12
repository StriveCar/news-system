package com.news.NS.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.NS.common.AlertException;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.FirstComment;
import com.news.NS.domain.SecondComment;
import com.news.NS.domain.dto.FirstCommentListQueryDTO;
import com.news.NS.domain.dto.FirstCommentUpdateDTO;
import com.news.NS.domain.dto.SecondCommentListQueryDTO;
import com.news.NS.domain.dto.SecondCommentUpdateDTO;
import com.news.NS.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

@Service
public class CommentService {

    private final FirstCommentMapper firstCommentMapper;

    private final SecondCommentMapper secondCommentMapper;

    private final NewsMapper newsMapper;

    private final UserMapper userMapper;

    public CommentService(
            FirstCommentMapper firstCommentMapper,
            SecondCommentMapper secondCommentMapper,
            NewsMapper newsMapper,
            UserMapper userMapper
    ) {
        this.firstCommentMapper = firstCommentMapper;
        this.secondCommentMapper = secondCommentMapper;
        this.newsMapper = newsMapper;
        this.userMapper = userMapper;
    }

    /**
     * 查询一级评论列表
     */
    public PageInfo<FirstComment> queryFirstCommentList(FirstCommentListQueryDTO dto) {
        Integer page = dto.getPage();
        Integer size = dto.getSize();
        Integer newsId = dto.getNewsId();
        Integer publisherId = dto.getPublisherId();
        Long publisherTime = dto.getPublishTime();

        // 无有效参数
//        if (newsId == null && publisherId == null && publisherTime == null) {
//            throw new AlertException(ResultCode.PARAM_NOT_COMPLETE);
//        }

        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder builder = select(FirstCommentMapper.selectList)
                .from(FirstCommentDynamicSqlSupport.firstComment)
                .where();

        // 指定了新闻
        if (newsId != null) {
            if (newsMapper.count(c -> c.where(NewsDynamicSqlSupport.newsId, isEqualTo(newsId))) != 1) { // 数据库不存在该 newsId
                throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该新闻不存在");
            }
            builder.and(FirstCommentDynamicSqlSupport.newsId, isEqualTo(newsId));
        }
        // 指定了评论者
        if (publisherId != null) {
            if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(publisherId)))!= 1) { // 数据库不存在该 publisherId
                throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该用户不存在");
            }
            builder.and(FirstCommentDynamicSqlSupport.publisherId, isEqualTo(publisherId));
        }
        // 指定了评论时间
        if (publisherTime != null) {
            builder.and(FirstCommentDynamicSqlSupport.publishTime, isEqualTo(new Date(publisherTime)));
        }

        SelectStatementProvider statement = builder
                .orderBy(FirstCommentDynamicSqlSupport.publishTime.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        Page<FirstComment> queryPage = PageHelper.startPage(page, size);
        List<FirstComment> result = firstCommentMapper.selectMany(statement);
        PageInfo<FirstComment> pageInfo = new PageInfo<>();
        pageInfo.setPage(page);
        pageInfo.setPageData(result);
        pageInfo.setTotalSize(queryPage.getTotal());
        return pageInfo;
    }

    /**
     * 添加一级评论
     */
    public void addFirstComment(FirstCommentUpdateDTO dto) {
        Integer newsId = dto.getNewsId();
        Integer publisherId = dto.getPublisherId();
        String content = dto.getContent();
        // 新闻不存在
        if (newsMapper.count(c -> c.where(NewsDynamicSqlSupport.newsId, isEqualTo(newsId))) == 0) { // 数据库不存在该 newsId
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该新闻不存在");
        }
        // 评论者不存在
        if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(publisherId))) == 0) {
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该用户不存在");
        }

        FirstComment comment = new FirstComment();
        comment.setNewsId(newsId);
        comment.setPublisherId(publisherId);
        comment.setContent(content);
        comment.setLikeNumber(0);
        comment.setPublishTime(new Date(System.currentTimeMillis()));
        firstCommentMapper.insert(comment);
    }


    /**
     * 删除一级评论
     */
    public void deleteFirstComment(Integer commentId) {
        if (firstCommentMapper.delete(c -> c.where(FirstCommentDynamicSqlSupport.commentId, isEqualTo(commentId))) == 0) {
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该评论不存在");
        }
        // 同时删除该一级评论下面的所有二级评论
        secondCommentMapper.delete(c -> c.where(SecondCommentDynamicSqlSupport.parentCommentId, isEqualTo(commentId)));
    }

    /**
     * 查询二级评论列表
     */
    public PageInfo<SecondComment> querySecondCommentList(SecondCommentListQueryDTO dto) {
        Integer page = dto.getPage();
        Integer size = dto.getSize();
        Integer parentCommentId = dto.getParentCommentId();
        Integer publisherId = dto.getPublisherId();
        Long publisherTime = dto.getPublishTime();

        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder builder = select(SecondCommentMapper.selectList)
                .from(SecondCommentDynamicSqlSupport.secondComment)
                .where(SecondCommentDynamicSqlSupport.parentCommentId, isEqualTo(parentCommentId));

        // 指定了评论者
        if (publisherId != null) {
            if (newsMapper.count(c -> c.where(NewsDynamicSqlSupport.publisherId, isEqualTo(publisherId))) != 1) { // 数据库不存在该 newsId
                throw new AlertException(400, "该用户不存在");
            }
            builder.and(FirstCommentDynamicSqlSupport.publisherId, isEqualTo(publisherId));
        }

        // 指定了评论时间
        if (publisherTime != null) {
            builder.and(FirstCommentDynamicSqlSupport.publishTime, isEqualTo(new Date(publisherTime)));
        }

        SelectStatementProvider statement = builder
                .orderBy(FirstCommentDynamicSqlSupport.publishTime.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        Page<SecondComment> queryPage = PageHelper.startPage(page, size);
        List<SecondComment> result = secondCommentMapper.selectMany(statement);
        PageInfo<SecondComment> pageInfo = new PageInfo<>();
        pageInfo.setPage(page);
        pageInfo.setPageData(result);
        pageInfo.setTotalSize(queryPage.getTotal());
        return pageInfo;
    }


    /**
     * 添加二级评论
     */
    public void addSecondComment(SecondCommentUpdateDTO dto) {
        Integer firstCommentId = dto.getCommentId();
        Integer publisherId = dto.getPublisherId();
        String content = dto.getContent();

        // 父评论不存在
        if (firstCommentMapper.count(c -> c.where(FirstCommentDynamicSqlSupport.commentId, isEqualTo(firstCommentId))) == 0) {
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该新闻不存在");
        }

        // 评论者不存在
        if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(publisherId))) == 0) {
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该用户不存在");
        }

        SecondComment comment = new SecondComment();
        comment.setParentCommentId(firstCommentId);
        comment.setPublisherId(publisherId);
        comment.setContent(content);
        comment.setLikeNumber(0);
        comment.setPublishTime(new Date(System.currentTimeMillis()));
        secondCommentMapper.insert(comment);
    }


    /**
     * 删除二级评论
     */
    public void deleteSecondComment(Integer commentId) {
        if (secondCommentMapper.delete(c -> c.where(SecondCommentDynamicSqlSupport.commentId, isEqualTo(commentId))) == 0) {
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该评论不存在");
        }
    }


}
