package com.news.NS.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.NS.common.AlertException;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.News;
import com.news.NS.domain.dto.NewsCreateDTO;
import com.news.NS.domain.dto.NewsSearchParamDTO;
import com.news.NS.mapper.NewsDynamicSqlSupport;
import com.news.NS.mapper.NewsMapper;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class NewsService {
    private final NewsMapper newsMapper;

    public NewsService(NewsMapper newsMapper){this.newsMapper = newsMapper;}
    public void create(NewsCreateDTO newsDTO) {
        News temp = new News();
        temp.setPublisherId(newsDTO.getPublisherId());
        temp.setSectionId(newsDTO.getSectionId());
        temp.setTitle(newsDTO.getTitle());
        temp.setContent(newsDTO.getContent());
        temp.setPublishStatus(CommonConstant.NEWS_NOTISSUE);
        temp.setNewsViews(0);
        temp.setLikeNumber(0);
        newsMapper.insert(temp);
    }

    public void delete(String ids) {
        System.out.println(ids);
        //传入的字符串含多个id，使用逗号分开
        if(ids.charAt(ids.length()-1) != ','){
            ids = ids + ",";
        }
        String[] sts = ids.split(",");
        int res = 0;
        int[] id = new int[sts.length];
        for (int i=0;i<id.length;i++) {
            id[i] = Integer.parseInt(sts[i]);
        }
        //删除多个新闻
        for (int i=0;i<id.length;i++) {
            UpdateStatementProvider updateStatement = update(NewsDynamicSqlSupport.news)
                    .set(NewsDynamicSqlSupport.publishStatus).equalTo(CommonConstant.NEWS_DISABLE)
                    .where(NewsDynamicSqlSupport.newsId,isEqualTo(id[i]))
                    .build().render(RenderingStrategies.MYBATIS3);
            if(newsMapper.update(updateStatement) == 0){
                throw new AlertException(ResultCode.DELETE_ERROR);
            }
        }
    }

    public void modifyNews(News news) {
        if (newsMapper.updateByPrimaryKey(news) != 1) {
            throw new AlertException(ResultCode.UPDATE_ERROR);
        }
    }

    public void publish(int newsId) {
        LocalDateTime currentTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentTime);
        Optional<News> optional = newsMapper.selectByPrimaryKey(newsId);
        if(!optional.isPresent()){
            throw new AlertException(500,"id为"+newsId+"的新闻不存在");
        } else {
            if(optional.get().getPublishStatus() != CommonConstant.NEWS_NOTISSUE) {
                throw new AlertException(ResultCode.ILLEGAL_OPERATION);
            }
        }
        UpdateStatementProvider updateStatement = update(NewsDynamicSqlSupport.news)
                .set(NewsDynamicSqlSupport.publishStatus).equalTo(CommonConstant.NEWS_ISSUE)
                .set(NewsDynamicSqlSupport.publishTime).equalTo(timestamp)
                .where(NewsDynamicSqlSupport.newsId,isEqualTo(newsId))
                .and(NewsDynamicSqlSupport.publishStatus,isNotEqualTo(CommonConstant.NEWS_DISABLE))
                .build().render(RenderingStrategies.MYBATIS3);
        int rows = newsMapper.update(updateStatement);
        if(rows == 0){
            throw new AlertException(ResultCode.UPDATE_ERROR);
        }
    }

    public Map<String,Object> getNewsById(Integer id){
        SelectStatementProvider sqlStatement = select(newsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.newsId,isEqualTo(id))
                .build().render(RenderingStrategies.MYBATIS3);
        Optional<News> optional = newsMapper.selectByPrimaryKey(id);
        if(optional.isPresent()){
            News news = optional.get();
            if(news.getPublishStatus() == CommonConstant.NEWS_DISABLE){
                throw new AlertException(500,"新闻已删除");
            } else {
                Map<String,Object> map = new HashMap<>();
                map.put("news",news);
                updateViews(news);
                return map;
            }
        } else {
            throw new AlertException(500,"新闻不存在");
        }
    }

    public void updateViews(News news){
        UpdateStatementProvider updateStatement = update(NewsDynamicSqlSupport.news)
                .set(NewsDynamicSqlSupport.newsViews).equalTo(news.getNewsViews()+1)
                .where(NewsDynamicSqlSupport.newsId,isEqualTo(news.getNewsId()))
                .build().render(RenderingStrategies.MYBATIS3);
        newsMapper.update(updateStatement);
    }

    public PageInfo<News> getNewsBySectionId(NewsSearchParamDTO<Integer> dto){
        SelectStatementProvider sqlStatement = select(newsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.sectionId,isEqualTo(dto.getParam()))
                .build().render(RenderingStrategies.MYBATIS3);

        Page<News> queryPageData = PageHelper.startPage(dto.getPage(), dto.getSize());
        List<News> news = newsMapper.selectMany(sqlStatement);
        return packing(news,dto.getPage(),queryPageData.getTotal());
    }

    public PageInfo<News> getNewsByPublisherId(NewsSearchParamDTO<Integer> dto) {
        SelectStatementProvider sqlStatement = select(newsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.publisherId,isEqualTo(dto.getParam()))
                .build().render(RenderingStrategies.MYBATIS3);

        Page<News> queryPageData = PageHelper.startPage(dto.getPage(), dto.getSize());
        List<News> news = newsMapper.selectMany(sqlStatement);
        return packing(news,dto.getPage(),queryPageData.getTotal());
    }

    public PageInfo<News> getAllNews(Integer page, Integer size) {
        SelectStatementProvider queryStatement = select(newsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.publishStatus,isNotEqualTo(CommonConstant.NEWS_DISABLE))
                .build().render(RenderingStrategies.MYBATIS3);

        Page<News> queryPageData = PageHelper.startPage(page, size);
        List<News> news = newsMapper.selectMany(queryStatement);
        return packing(news,page,queryPageData.getTotal());
    }

    private PageInfo<News> packing(List<News> news,Integer page,long total){
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

    public PageInfo<News> searchNews(NewsSearchParamDTO<String> dto) {
        String likeKeyword = "%" + dto.getParam() + "%";

        SelectStatementProvider selectStatement = select(newsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.title,isLike(likeKeyword))
                .or(NewsDynamicSqlSupport.content,isLike(likeKeyword))
                .and(NewsDynamicSqlSupport.publishStatus,isNotEqualTo(CommonConstant.NEWS_DISABLE))
                .build().render(RenderingStrategies.MYBATIS3);

        Page<News> queryPageData = PageHelper.startPage(dto.getPage(), dto.getSize());
        List<News> newsList = newsMapper.selectMany(selectStatement);

        return packing(newsList,dto.getPage(), queryPageData.getTotal());
    }

    public PageInfo<News> getNewsByPublishStatus(NewsSearchParamDTO<Byte> dto) {
        if(dto.getParam() < 1 || dto.getParam() > 4){
            throw new AlertException(406,"非法参数："+dto.getParam());
        }
        SelectStatementProvider sqlStatement = select(newsMapper.selectList)
                .from(NewsDynamicSqlSupport.news)
                .where(NewsDynamicSqlSupport.publishStatus,isEqualTo(dto.getParam()))
                .build().render(RenderingStrategies.MYBATIS3);

        Page<News> queryPageData = PageHelper.startPage(dto.getPage(), dto.getSize());
        List<News> news = newsMapper.selectMany(sqlStatement);

        return packing(news,dto.getPage(),queryPageData.getTotal());
    }
}
