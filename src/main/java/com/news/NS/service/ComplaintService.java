package com.news.NS.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.NS.common.AlertException;
import com.news.NS.common.CommonConstant;
import com.news.NS.common.domain.PageInfo;
import com.news.NS.common.domain.ResultCode;
import com.news.NS.domain.Complaint;
import com.news.NS.domain.News;
import com.news.NS.domain.User;
import com.news.NS.domain.dto.ComplaintCreateDTO;
import com.news.NS.domain.dto.ComplaintDeleteDTO;
import com.news.NS.domain.dto.ComplaintModifyDTO;
import com.news.NS.domain.dto.ComplaintSearchDTO;
import com.news.NS.mapper.*;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public void addNewComplaint(ComplaintCreateDTO complaintCreateDTO) {
        Complaint temp = new Complaint();
        //检查用户是否存在。
        if(!userMapper.selectByPrimaryKey(complaintCreateDTO.getComplainerId()).isPresent()){
            throw new AlertException(ResultCode.USER_NOT_EXIST);
        }
        temp.setComplainerId(complaintCreateDTO.getComplainerId());
        //检查新闻是否存在
        if(!newsMapper.selectByPrimaryKey(complaintCreateDTO.getNewsId()).isPresent()){
            throw new AlertException(500,"id为"+complaintCreateDTO.getNewsId()+"新闻不存在");
        }
        temp.setNewsId(complaintCreateDTO.getNewsId());
        temp.setComplaintTime(Timestamp.valueOf(LocalDateTime.now()));
        temp.setComplaintReason(complaintCreateDTO.getReason());
        complaintMapper.insert(temp);
    }

    public void deleteComplaint(ComplaintDeleteDTO dto) {
        if(complaintMapper.deleteByPrimaryKey(dto.getComplaintId(),dto.getNewsId()) == 0) {
            throw new AlertException(ResultCode.DELETE_ERROR);
        }
    }

    public void modifyComplaint(ComplaintModifyDTO dto) {
        UpdateStatementProvider updateStatement = update(ComplaintDynamicSqlSupport.complaint)
                .set(ComplaintDynamicSqlSupport.complaintReason).equalTo(dto.getReason())
                .where(ComplaintDynamicSqlSupport.complainerId,isEqualTo(dto.getComplaintId()))
                .build().render(RenderingStrategies.MYBATIS3);
        if(complaintMapper.update(updateStatement) != 1){
            throw new AlertException(ResultCode.UPDATE_ERROR);
        }
    }

    public PageInfo<Complaint> searchComplaint(ComplaintSearchDTO<String> dto) {
        String likeKeyword = "%" + dto.getSearchKeyword() + "%";

        SelectStatementProvider selectStatement = select(complaintMapper.selectList)
                .from(ComplaintDynamicSqlSupport.complaint)
                .where(ComplaintDynamicSqlSupport.complaintReason,isLike(likeKeyword))
                .build().render(RenderingStrategies.MYBATIS3);

        Page<Complaint> queryPageData = PageHelper.startPage(dto.getPage(), dto.getSize());
        List<Complaint> complaints = complaintMapper.selectMany(selectStatement);

        return packing(complaints,dto.getPage(), queryPageData.getTotal());
    }
    private PageInfo<Complaint> packing(List<Complaint> complaints,Integer page,long total){
        /*
         * 把多个News分页封装成Pageinfo然后返回
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
}
