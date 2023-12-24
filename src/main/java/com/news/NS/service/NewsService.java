package com.news.NS.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.NS.common.AlertException;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.News;
import com.news.NS.domain.Section;
import com.news.NS.domain.User;
import com.news.NS.domain.dto.News.NewsCreateDTO;
import com.news.NS.domain.dto.News.NewsGetDTO;
import com.news.NS.domain.dto.News.NewsListDTO;
import com.news.NS.domain.dto.News.NewsGetByParamDTO;
import com.news.NS.domain.vo.NewsListVo;
import com.news.NS.mapper.*;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;



@Service
public class NewsService {
    private final NewsMapper newsMapper;
    private final UserMapper userMapper;
    private final SectionMapper sectionMapper;
    private final CollectMapper collectMapper;
    public NewsService(NewsMapper newsMapper,
                       UserMapper userMapper,
                       SectionMapper sectionMapper,
                       CollectMapper collectMapper){
        this.newsMapper = newsMapper;
        this.userMapper = userMapper;
        this.sectionMapper = sectionMapper;
        this.collectMapper = collectMapper;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void create(NewsCreateDTO newsDTO) {
        News temp = new News();
        //用户身份校验
        Optional<User> optional = userMapper.selectByPrimaryKey(newsDTO.getPublisherId());
        if (!optional.isPresent()){
            throw new AlertException(ResultCode.USER_NOT_EXIST);
        } else {
            if(!optional.get().getIdentification().equals(Byte.valueOf(CommonConstant.PULISHER))){
                throw new AlertException(ResultCode.ILLEGAL_OPERATION);
            }
        }
        temp.setPublisherId(newsDTO.getPublisherId());
        Optional<Section> optional1 = sectionMapper.selectByPrimaryKey(newsDTO.getSectionId());
        if(!optional1.isPresent()){
            throw new AlertException(500,"栏目不存在");
        }
        temp.setSectionId(newsDTO.getSectionId());
        if(!StringUtils.hasLength(newsDTO.getTitle())){
            throw new AlertException(ResultCode.PARAM_IS_BLANK);
        }
        temp.setTitle(newsDTO.getTitle());
        if(!StringUtils.hasLength(newsDTO.getContent())){
            throw new AlertException(ResultCode.PARAM_IS_BLANK);
        }
        temp.setContent(newsDTO.getContent());
        temp.setPublishStatus(CommonConstant.NEWS_NOTISSUE);
        temp.setNewsViews(0);
        temp.setLikeNumber(0);
        newsMapper.insert(temp);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(String ids) {
        System.out.println(ids);
        //传入的字符串含多个id，使用逗号分开
        if (ids.charAt(ids.length() - 1) != ',') {
            ids = ids + ",";
        }
        String[] sts = ids.split(",");
        int res = 0;
        int[] id = new int[sts.length];
        for (int i = 0; i < id.length; i++) {
            id[i] = Integer.parseInt(sts[i]);
        }
        //删除多个新闻
        for (int i = 0; i < id.length; i++) {
            UpdateStatementProvider updateStatement = update(NewsDynamicSqlSupport.news)
                    .set(NewsDynamicSqlSupport.publishStatus).equalTo(CommonConstant.NEWS_DISABLE)
                    .where(NewsDynamicSqlSupport.newsId, isEqualTo(id[i]))
                    .build().render(RenderingStrategies.MYBATIS3);
            if (newsMapper.update(updateStatement) == 0) {
                throw new AlertException(ResultCode.DELETE_ERROR);
            }
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyNews(News news) {
        if(!StringUtils.hasLength(news.getTitle()) || !StringUtils.hasLength(news.getContent())){
            throw new AlertException(ResultCode.PARAM_IS_BLANK);
        }
        if(news.getPublishStatus().equals(CommonConstant.NEWS_DISABLE)){
            throw new AlertException(ResultCode.ILLEGAL_OPERATION);
        }
        if (newsMapper.updateByPrimaryKey(news) != 1) {
            throw new AlertException(ResultCode.UPDATE_ERROR);
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void publish(Integer newsId) {
        LocalDateTime currentTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentTime);
        Optional<News> optional = newsMapper.selectByPrimaryKey(newsId);
        if(optional.isEmpty()){
            throw new AlertException(500,"新闻不存在");
        } else {
            Byte status = optional.get().getPublishStatus();
            if(!(status.equals(CommonConstant.NEWS_NOTISSUE) || status.equals(CommonConstant.NEWS_REVIEW_REJECT) || status.equals(CommonConstant.NEWS_CANCEL_ISSUE))) {
                throw new AlertException(ResultCode.ILLEGAL_OPERATION);
            }
        }
        UpdateStatementProvider updateStatement = update(NewsDynamicSqlSupport.news)
                .set(NewsDynamicSqlSupport.publishStatus).equalTo(CommonConstant.NEWS_REVIEWING)
                .set(NewsDynamicSqlSupport.publishTime).equalTo(timestamp)
                .where(NewsDynamicSqlSupport.newsId, isEqualTo(newsId))
                .and(NewsDynamicSqlSupport.publishStatus, isNotEqualTo(CommonConstant.NEWS_DISABLE))
                .build().render(RenderingStrategies.MYBATIS3);
        int rows = newsMapper.update(updateStatement);
        if (rows == 0) {
            throw new AlertException(ResultCode.UPDATE_ERROR);
        }
    }


    public Map<String,Object> getNewsById(NewsGetDTO newsGetDTO){
        SelectStatementProvider sqlStatement = select(newsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.newsId,isEqualTo(newsGetDTO.getNewsId()))
                .build().render(RenderingStrategies.MYBATIS3);
        Optional<News> optional = newsMapper.selectByPrimaryKey(newsGetDTO.getNewsId());
        if(optional.isPresent()){
            News news = optional.get();
            if(news.getPublishStatus().equals(CommonConstant.NEWS_DISABLE)){
                throw new AlertException(500,"新闻已删除");
            }
            Map<String,Object> map = new HashMap<>();
            map.put("news",news);
            updateViews(news);
            //取作者昵称、头像
            Optional<User> optional1 = userMapper.selectByPrimaryKey(news.getPublisherId());
            if(optional1.isPresent()){
                User user = optional1.get();
                map.put("publisherName",user.getUsername());
                map.put("avatar_url",user.getAvatarUrl());
            } else {
                throw new AlertException(ResultCode.USER_NOT_EXIST);
            }

            //读者是否关注了该新闻的作者
            if(newsMapper.judgeFocusByUserId(newsGetDTO.getUserId(), news.getPublisherId()) == 1){
                map.put("isFocusPublisher",true);
            } else {
                map.put("isFocusPublisher",false);
            }
            //读者是否收藏该新闻
            if(collectMapper.selectByPrimaryKey(newsGetDTO.getUserId(), newsGetDTO.getNewsId()).isPresent()){
                map.put("isCollect",true);
            } else {
                map.put("isCollect",false);
            }
            return map;
        } else {
            throw new AlertException(500,"新闻不存在");
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void updateViews(News news) {
        UpdateStatementProvider updateStatement = update(NewsDynamicSqlSupport.news)
                .set(NewsDynamicSqlSupport.newsViews).equalTo(news.getNewsViews() + 1)
                .where(NewsDynamicSqlSupport.newsId, isEqualTo(news.getNewsId()))
                .build().render(RenderingStrategies.MYBATIS3);
        newsMapper.update(updateStatement);
    }

    public PageInfo<News> getNewsBySectionId(NewsGetByParamDTO<Integer> dto){
        Optional<Section> optional = sectionMapper.selectByPrimaryKey(dto.getParam());
        if(optional.isEmpty()){
            throw new AlertException(500,"栏目不存在");
        }
        SelectStatementProvider sqlStatement = select(newsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.sectionId,isEqualTo(dto.getParam()))
                .build().render(RenderingStrategies.MYBATIS3);

        Page<News> queryPageData = PageHelper.startPage(dto.getPage(), dto.getSize());
        List<News> news = newsMapper.selectMany(sqlStatement);
        queryPageData.setTotal(news.size());
        return packing(news,dto.getPage(),queryPageData.getTotal());
    }

    public PageInfo<News> getNewsByPublisherId(NewsGetByParamDTO<Integer> dto) {

        SelectStatementProvider sqlStatement = select(newsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.publisherId,isEqualTo(dto.getParam()))
                .build().render(RenderingStrategies.MYBATIS3);

        Page<News> queryPageData = PageHelper.startPage(dto.getPage(),dto.getSize());
        List<News> news = newsMapper.selectMany(sqlStatement);
        queryPageData.setTotal(news.size());
        return packing(news,dto.getPage(),queryPageData.getTotal());
    }

    public PageInfo<NewsListVo> getNewsList(NewsListDTO dto) {
        String content = "%" + dto.getContent() + "%";
        String title = "%" + dto.getTitle() + "%";
        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder queryExpressionWhereBuilder = select(newsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.title, isLikeWhenPresent(title))
                .and(NewsDynamicSqlSupport.content, isLike(content))
                .and(NewsDynamicSqlSupport.publishStatus, isEqualTo(dto.getStatus()));

        if (dto.getSectionId() != null) {
            queryExpressionWhereBuilder.and(NewsDynamicSqlSupport.sectionId, isEqualTo(dto.getSectionId()));
        }

        SelectStatementProvider selectStatement = queryExpressionWhereBuilder
                .orderBy(NewsDynamicSqlSupport.publishTime.descending())
                .build().render(RenderingStrategies.MYBATIS3);

        Page<News> queryPageData = PageHelper.startPage(dto.getPage(), dto.getSize());
        List<News> newsList = newsMapper.selectMany(selectStatement);

        BeanCopier newsCopier = BeanCopier.create(News.class, NewsListVo.class, false);
        List<NewsListVo> newsListVos = newsList.stream().map(item -> {
            NewsListVo newsListVo = new NewsListVo();
            newsCopier.copy(item, newsListVo, null);
            newsListVo.setPublisherName(userMapper.selectByPrimaryKey(item.getPublisherId()).get().getUsername());
            newsListVo.setSectionName(sectionMapper.selectByPrimaryKey(item.getSectionId()).get().getSectionName());
            return newsListVo;
        }).collect(Collectors.toList());
        PageInfo<NewsListVo> pageInfo = new PageInfo<>();
        pageInfo.setPageData(newsListVos);
        pageInfo.setPage(dto.getPage());
        pageInfo.setTotalSize(queryPageData.getTotal());
        return pageInfo;
    }

    private PageInfo<News> packing(List<News> news, Integer page, long total) {
        /*
         * 把多个News分页封装成Pageinfo然后返回
         * news：News数据
         * page：页数
         * size：每页的数据量
         * */
        PageInfo<News> pageInfo = new PageInfo<>();
        pageInfo.setPageData(news);
        pageInfo.setPage(page);
        pageInfo.setTotalSize(total);
        return pageInfo;
    }

    public PageInfo<News> searchNews(NewsGetByParamDTO<String> dto) {
        String likeKeyword = "%" + dto.getParam() + "%";


        SelectStatementProvider selectStatement = select(newsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.title, isLike(likeKeyword))
                .or(NewsDynamicSqlSupport.content, isLike(likeKeyword))
                .and(NewsDynamicSqlSupport.publishStatus, isNotEqualTo(CommonConstant.NEWS_DISABLE))
                .build().render(RenderingStrategies.MYBATIS3);

        Page<News> queryPageData = PageHelper.startPage(dto.getPage(), dto.getSize());
        List<News> newsList = newsMapper.selectMany(selectStatement);
        queryPageData.setTotal(newsList.size());
        return packing(newsList, dto.getPage(), queryPageData.getTotal());
    }

    public PageInfo<News> getNewsByPublishStatus(NewsGetByParamDTO<Byte> dto) {
        if(dto.getParam().compareTo(CommonConstant.NEWS_NOTISSUE) < 0 || dto.getParam().compareTo(CommonConstant.NEWS_REVIEW_REJECT) > 0){
            //新闻状态1-6
            throw new AlertException(ResultCode.PARAM_IS_ILLEGAL);
        }
        SelectStatementProvider sqlStatement = select(newsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.publishStatus, isEqualTo(dto.getParam()))
                .build().render(RenderingStrategies.MYBATIS3);

        Page<News> queryPageData = PageHelper.startPage(dto.getPage(), dto.getSize());
        List<News> news = newsMapper.selectMany(sqlStatement);

        return packing(news,dto.getPage(),queryPageData.getTotal());
    }


}
