package com.news.NS.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.NS.common.AlertException;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.Complaint;
import com.news.NS.domain.dto.Complaint.ComplaintCreateDTO;
import com.news.NS.domain.dto.Complaint.ComplaintDeleteDTO;
import com.news.NS.domain.dto.Complaint.ComplaintModifyDTO;
import com.news.NS.domain.dto.Complaint.ComplaintSearchDTO;
import com.news.NS.domain.News;
import com.news.NS.domain.User;
import com.news.NS.domain.dto.*;
import com.news.NS.domain.vo.ComplaintListVo;
import com.news.NS.domain.vo.NewsListVo;
import com.news.NS.mapper.*;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class ComplaintService {
    private final ComplaintMapper complaintMapper;
    private final UserMapper userMapper;
    private final NewsMapper newsMapper;
    public ComplaintService(ComplaintMapper complaintMapper,
                            NewsMapper newsMapper,
                            UserMapper userMapper){
        this.complaintMapper = complaintMapper;
        this.newsMapper = newsMapper;
        this.userMapper = userMapper;
    }
    @Transactional(rollbackFor = RuntimeException.class)
    public void addNewComplaint(ComplaintCreateDTO complaintCreateDTO) {
        Complaint temp = new Complaint();
        //检查用户是否存在。
        if(userMapper.count(c->c.where(UserDynamicSqlSupport.userId,isEqualTo(complaintCreateDTO.getComplainerId()))) == 0){
            throw new AlertException(ResultCode.USER_NOT_EXIST);
        }
        temp.setComplainerId(complaintCreateDTO.getComplainerId());
        //检查新闻是否存在
        if(newsMapper.count(c->c.where(NewsDynamicSqlSupport.newsId,isEqualTo(complaintCreateDTO.getNewsId()))) == 0){
            throw new AlertException(500,"新闻不存在");
        }
        temp.setNewsId(complaintCreateDTO.getNewsId());
        String st = complaintCreateDTO.getReason();
        if(StringUtils.hasLength(st)){
            temp.setComplaintReason(complaintCreateDTO.getReason());
        } else {
            throw new AlertException(ResultCode.PARAM_IS_BLANK);
        }

        temp.setComplaintTime(Timestamp.valueOf(LocalDateTime.now()));
        complaintMapper.insert(temp);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteComplaint(ComplaintDeleteDTO dto) {
        if(complaintMapper.deleteByPrimaryKey(dto.getComplainerId(),dto.getNewsId()) == 0) {
            throw new AlertException(ResultCode.DELETE_ERROR);
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyComplaint(ComplaintModifyDTO dto) {
        if(!StringUtils.hasLength(dto.getReason())){
            throw new AlertException(ResultCode.PARAM_IS_BLANK);
        }
        UpdateStatementProvider updateStatement = update(ComplaintDynamicSqlSupport.complaint)
                .set(ComplaintDynamicSqlSupport.complaintReason).equalTo(dto.getReason())
                .where(ComplaintDynamicSqlSupport.complainerId,isEqualTo(dto.getComplainerId()))
                .and(ComplaintDynamicSqlSupport.newsId,isEqualTo(dto.getNewsId()))
                .build().render(RenderingStrategies.MYBATIS3);
        if(complaintMapper.update(updateStatement) != 1){
            throw new AlertException(ResultCode.UPDATE_ERROR);
        }
    }

    public PageInfo<ComplaintListVo> searchComplaint(ComplaintListDTO dto) {
        final String reason = StringUtils.hasLength(dto.getReason()) ? dto.getReason() + "%" : null;
        final String name = StringUtils.hasLength(dto.getName()) ? dto.getName() + "%" : null;
        final String title = StringUtils.hasLength(dto.getTitle()) ? dto.getTitle() + "%" : null;

        SelectStatementProvider selectStatement = select(complaintMapper.selectList)
                .from(ComplaintDynamicSqlSupport.complaint)
                .where(ComplaintDynamicSqlSupport.complaintReason,isLikeWhenPresent(reason))
                .build().render(RenderingStrategies.MYBATIS3);

        List<Complaint> complaints = complaintMapper.selectMany(selectStatement);
        BeanCopier copier = BeanCopier.create(Complaint.class, ComplaintListVo.class, false);

        List<ComplaintListVo> complaintListVos = complaints.stream().map(item ->{
            ComplaintListVo complaintListVo = new ComplaintListVo();
            copier.copy(item,complaintListVo,null);
            Optional<News> news = newsMapper.selectOne(s -> s.where(NewsDynamicSqlSupport.newsId, isEqualTo(item.getNewsId()))
                    .and(NewsDynamicSqlSupport.title,isLikeWhenPresent(title)));
            if (news.isPresent()) {
                complaintListVo.setNewsContent(news.get().getContent());
                complaintListVo.setNewsTitle(news.get().getTitle());
            } else {
                return null;
            }
            Optional<User> user = userMapper.selectOne(s -> s.where(UserDynamicSqlSupport.userId, isEqualTo(item.getComplainerId()))
                    .and(UserDynamicSqlSupport.username,isLikeWhenPresent(name)));
            if (user.isPresent()) {
                complaintListVo.setComplainerName(user.get().getUsername());
            } else {
                return null;
            }
            return complaintListVo;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        int page = dto.getPage(); // 当前页码
        int pageSize = dto.getSize(); // 每页大小

        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, complaintListVos.size());

        List<ComplaintListVo> paginatedList = complaintListVos.subList(startIndex, endIndex);


        PageInfo<ComplaintListVo> pageInfo = new PageInfo<>();
        pageInfo.setPageData(paginatedList);
        pageInfo.setPage(dto.getPage());
        pageInfo.setTotalSize((long)complaintListVos.size());
        return pageInfo;
    }

    private PageInfo<Complaint> packing(List<Complaint> complaints,Integer page,long total){
        /*
         * List封装成Pageinfo然后返回
         * Complaint：Complaint数据
         * page：页数
         * size：每页的数据量
         * */
        PageInfo<Complaint> pageInfo = new PageInfo<>();
        pageInfo.setPageData(complaints);
        pageInfo.setPage(page);
        pageInfo.setTotalSize(total);
        return pageInfo;
    }

    public PageInfo<Complaint> getByComplainerId(ComplaintSearchDTO<Integer> dto) {
        SelectStatementProvider selectStatement = select(complaintMapper.selectList)
                .from(ComplaintDynamicSqlSupport.complaint)
                .where(ComplaintDynamicSqlSupport.complainerId,isEqualTo(dto.getSearchKeyword()))
                .build().render(RenderingStrategies.MYBATIS3);

        Page<Complaint> queryPageData = PageHelper.startPage(dto.getPage(), dto.getSize());
        List<Complaint> complaints = complaintMapper.selectMany(selectStatement);

        return packing(complaints,dto.getPage(), queryPageData.getTotal());
    }
}
