package com.news.NS.service;

import com.news.NS.domain.Complaint;
import com.news.NS.domain.dto.ComplaintCreateDTO;
import com.news.NS.mapper.ComplaintMapper;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {
    private final ComplaintMapper complaintMapper;
    public ComplaintService(ComplaintMapper complaintMapper){this.complaintMapper = complaintMapper;}
    public void addNewComplaint(ComplaintCreateDTO complaintCreateDTO) {
        Complaint temp = new Complaint();
        temp.setNewsId(complaintCreateDTO.getNewsId());
        temp.setComplaintTime(complaintCreateDTO.getComplaintTime());
        temp.setComplaintReason(complaintCreateDTO.getReason());
        complaintMapper.insert(temp);
    }
}
