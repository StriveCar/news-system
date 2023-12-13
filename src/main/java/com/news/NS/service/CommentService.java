package com.news.NS.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.NS.common.AlertException;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.FirstComment;
import com.news.NS.domain.News;
import com.news.NS.domain.SecondComment;
import com.news.NS.domain.User;
import com.news.NS.domain.dto.*;
import com.news.NS.domain.vo.CommentAdminVo;
import com.news.NS.domain.vo.FirstAndSecondCommentVo;
import com.news.NS.domain.vo.FirstCommentVo;
import com.news.NS.domain.vo.SecondCommentVo;
import com.news.NS.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.CountDSL;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

@Service
@Slf4j
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
    public PageInfo<FirstCommentVo> queryFirstCommentList(FirstCommentListQueryDTO dto) {
        Integer page = dto.getPage();
        Integer size = dto.getSize();
        Integer newsId = dto.getNewsId();
        Integer publisherId = dto.getPublisherId();
        Long publisherTime = dto.getPublishTime();

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
            if (userMapper.count(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(publisherId))) != 1) { // 数据库不存在该 publisherId
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

        Page<FirstCommentVo> queryPage = PageHelper.startPage(page, size);
        List<FirstComment> comments = firstCommentMapper.selectMany(statement);
        // 对于每个评论查询用户信息
        List<FirstCommentVo> voList = comments.stream().map(item -> {
            FirstCommentVo vo = new FirstCommentVo();
            vo.setPublisher(queryUser(item.getPublisherId()));
            vo.setCommentId(item.getCommentId());
            vo.setLikeNumber(item.getLikeNumber());
            vo.setContent(item.getContent());
            vo.setPublishTime(item.getPublishTime().getTime());
            return vo;
        }).collect(Collectors.toList());

        return generatePageInfo(queryPage, voList);
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
     * 点赞一级评论，评论数+1
     */
    public void likeFirstComment(Integer commentId) {
        Optional<FirstComment> comment = firstCommentMapper.selectOne(c -> c.where(FirstCommentDynamicSqlSupport.commentId, isEqualTo(commentId)));
        if (comment.isPresent()) {
            // 点赞数量 + 1
            int likeNumber = comment.get().getLikeNumber() + 1;
            firstCommentMapper.update(c ->
                    c.set(FirstCommentDynamicSqlSupport.likeNumber).equalTo(likeNumber).where(FirstCommentDynamicSqlSupport.commentId, isEqualTo(commentId))
            );
        } else {
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该评论不存在");
        }
    }

    /**
     * 查询二级评论列表
     */
    public PageInfo<SecondCommentVo> querySecondCommentList(SecondCommentListQueryDTO dto) {
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
        List<SecondComment> comments = secondCommentMapper.selectMany(statement);

        // 对每个评论查询用户
        List<SecondCommentVo> voList = comments.stream().map(item -> {
            SecondCommentVo vo = new SecondCommentVo();
            vo.setPublisher(queryUser(item.getPublisherId()));
            vo.setCommentId(item.getCommentId());
            vo.setParentCommentId(item.getParentCommentId());
            vo.setPublishTime(item.getPublishTime().getTime());
            vo.setLikeNumber(item.getLikeNumber());
            vo.setContent(item.getContent());
            return vo;
        }).collect(Collectors.toList());

        return generatePageInfo(queryPage, voList);
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


    /**
     * 点赞一级评论，评论数+1
     */
    public void likeSecondComment(Integer commentId) {
        Optional<SecondComment> comment = secondCommentMapper.selectOne(c -> c.where(SecondCommentDynamicSqlSupport.commentId, isEqualTo(commentId)));
        if (comment.isPresent()) {
            int likeNumber = comment.get().getLikeNumber() + 1;
            secondCommentMapper.update(c ->
                    c.set(SecondCommentDynamicSqlSupport.likeNumber).equalTo(likeNumber).where(SecondCommentDynamicSqlSupport.commentId, isEqualTo(commentId))
            );
        } else {
            throw new AlertException(ResultCode.PARAM_IS_INVALID.code(), "该评论不存在");
        }
    }


    /**
     * 返回带有二级评论的一级评论
     */
    public PageInfo<FirstAndSecondCommentVo> queryFirstAndSecondCommentList(CommentListQueryDTO dto) {
        Integer page = dto.getPage();
        Integer size = dto.getSize();
        Integer newsId = dto.getNewsId();

        if (newsMapper.count(c -> c.where(NewsDynamicSqlSupport.newsId, isEqualTo(newsId))) == 0) {
            throw new AlertException(400, "该新闻不存在");
        }

        Page<FirstComment> queryPage = PageHelper.startPage(page, size);
        List<FirstComment> firstComments = firstCommentMapper.select(c ->
                c.where(FirstCommentDynamicSqlSupport.newsId, isEqualTo(newsId))
        );
        List<FirstAndSecondCommentVo> result = firstComments.stream().map(item -> {
            FirstAndSecondCommentVo vo = new FirstAndSecondCommentVo();
            vo.setCommentId(item.getCommentId());
            vo.setContent(item.getContent());
            vo.setLikeNumber(item.getLikeNumber());
            vo.setPublishTime(item.getPublishTime().getTime());
            vo.setPublisher(queryUser(item.getPublisherId()));

            // 查询最多五条二级评论,
            List<SecondCommentVo> secondComments = secondCommentMapper.select(c ->
                            c.where(SecondCommentDynamicSqlSupport.parentCommentId, isEqualTo(item.getCommentId()))
                                    .limit(5)
                    ) // 处理数据
                    .stream().map(secondComment -> {
                        SecondCommentVo secondCommentVo = new SecondCommentVo();
                        secondCommentVo.setCommentId(secondComment.getCommentId());
                        secondCommentVo.setParentCommentId(secondComment.getParentCommentId());
                        secondCommentVo.setPublishTime(secondComment.getPublishTime().getTime());
                        secondCommentVo.setLikeNumber(secondComment.getLikeNumber());
                        secondCommentVo.setContent(secondComment.getContent());
                        secondCommentVo.setPublisher(queryUser(secondComment.getPublisherId()));
                        return secondCommentVo;
                    }).collect(Collectors.toList());

            vo.setSecondComments(secondComments);
            return vo;
        }).collect(Collectors.toList());

        return generatePageInfo(queryPage, result);
    }

    /**
     * 返回评论数量
     */
    public long queryCommentCount() {
        return firstCommentMapper.count(CountDSL::where) + secondCommentMapper.count(CountDSL::where);
    }


    /**
     * 返回管理端所需要的所有一级评论
     */
    public PageInfo<CommentAdminVo> queryFirstCommentAdminList(CommentListAdminQueryDTO dto) {
        Integer page = dto.getPage();
        Integer size = dto.getSize();
        Integer newsId = dto.getNewsId();

        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder builder = select(FirstCommentMapper.selectList)
                .from(FirstCommentDynamicSqlSupport.firstComment)
                .where();

        if (newsId != null) {
            // 数据库不存在该 newsId
            if (newsMapper.count(c -> c.where(NewsDynamicSqlSupport.newsId, isEqualTo(newsId))) != 1) {
                throw new AlertException(400, "该新闻不存在");
            }
            builder.and(FirstCommentDynamicSqlSupport.newsId, isEqualTo(newsId));
        }

        SelectStatementProvider firstStatement = builder
                .build()
                .render(RenderingStrategies.MYBATIS3);

        Page<FirstComment> queryPage = PageHelper.startPage(page, size);
        List<FirstComment> firstComments = firstCommentMapper.selectMany(firstStatement);

        List<CommentAdminVo> result = firstComments.stream().map(item -> {
            CommentAdminVo vo = new CommentAdminVo();
            vo.setParentCommentContent(null);
            vo.setCommentId(item.getCommentId());
            vo.setPublishTime(item.getPublishTime().getTime());
            vo.setContent(item.getContent());
            vo.setLikeNumber(item.getLikeNumber());
            Optional<News> news = newsMapper.selectOne(s -> s.where(NewsDynamicSqlSupport.newsId, isEqualTo(item.getNewsId())));
            if (news.isPresent()) {
                vo.setNewsContent(news.get().getContent());
                vo.setNewsTitle(news.get().getTitle());
            } else {
                vo.setNewsContent(null);
                vo.setNewsTitle(null);
            }
            vo.setPublisher(queryUser(item.getPublisherId()));
            vo.setParentCommentContent(null);
            vo.setParentCommentId(-1);
            return vo;
        }).collect(Collectors.toList());

        return generatePageInfo(queryPage, result);
    }

    /**
     * 返回管理端所需要的所有二级评论
     *
     * 不支持根据新闻id查找
     */
    public PageInfo<CommentAdminVo> querySecondCommentAdminList(CommentListAdminQueryDTO dto) {
        Integer page = dto.getPage();
        Integer size = dto.getSize();
//        Integer newsId = dto.getNewsId();

//        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder builder = select(FirstCommentMapper.selectList)
//                .from(FirstCommentDynamicSqlSupport.firstComment)
//                .where();

//        if (newsId != null) {
//            // 数据库不存在该 newsId
//            if (newsMapper.count(c -> c.where(NewsDynamicSqlSupport.newsId, isEqualTo(newsId))) != 1) {
//                throw new AlertException(400, "该新闻不存在");
//            }
//            builder.and(FirstCommentDynamicSqlSupport.newsId, isEqualTo(newsId));
//        }

//        SelectStatementProvider statement = builder
//                .build()
//                .render(RenderingStrategies.MYBATIS3);

        SelectStatementProvider secondStatement = select(SecondCommentMapper.selectList)
                .from(SecondCommentDynamicSqlSupport.secondComment)
                .where()
                .build()
                .render(RenderingStrategies.MYBATIS3);

        Page<SecondComment> queryPage = PageHelper.startPage(page, size);
//        List<FirstComment> firstComments = firstCommentMapper.selectMany(statement);
        List<SecondComment> secondComments = secondCommentMapper.selectMany(secondStatement);

        List<CommentAdminVo> result = secondComments.stream().map(item -> {
            CommentAdminVo vo = new CommentAdminVo();
            Optional<FirstComment> firstComment = firstCommentMapper.selectOne(s -> s.where(FirstCommentDynamicSqlSupport.commentId, isEqualTo(item.getParentCommentId())));
            if (firstComment.isPresent()) {
                vo.setParentCommentId(firstComment.get().getCommentId());
                vo.setParentCommentContent(firstComment.get().getContent());

                Optional<News> news = newsMapper.selectOne(s -> s.where(NewsDynamicSqlSupport.newsId, isEqualTo(firstComment.get().getNewsId())));
                if (news.isPresent()) {
                    vo.setNewsContent(news.get().getContent());
                    vo.setNewsTitle(news.get().getTitle());
                } else {
                    vo.setNewsContent(null);
                    vo.setNewsTitle(null);
                }
            }
            vo.setPublisher(queryUser(item.getPublisherId()));

            vo.setCommentId(item.getCommentId());
            vo.setPublishTime(item.getPublishTime().getTime());
            vo.setContent(item.getContent());
            vo.setLikeNumber(item.getLikeNumber());

            return vo;
        }).collect(Collectors.toList());

        return generatePageInfo(queryPage, result);
    }

    /**
     * 查询指定用户
     */
    private User queryUser(Integer userId) {
        Optional<User> user = userMapper.selectOne(c -> c.where(UserDynamicSqlSupport.userId, isEqualTo(userId)));
        if (user.isPresent()) {
            user.get().setPassword(null);
            return user.get();
        }
        return null;
    }

    private <T> PageInfo<T> generatePageInfo(Page<?> page, List<T> result) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setPage(page.getPageNum());
        pageInfo.setTotalSize(page.getTotal());
        pageInfo.setPageData(result);
        return pageInfo;
    }

}
