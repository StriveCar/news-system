package com.news.NS.domain;

import java.util.Date;
import javax.annotation.Generated;

public class Focus {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2340808+08:00", comments="Source field: user_focus.user_id")
    private Integer userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2370706+08:00", comments="Source field: user_focus.focused_user_id")
    private Integer focusedUserId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2370706+08:00", comments="Source field: user_focus.focus_time")
    private Date focusTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2370706+08:00", comments="Source field: user_focus.user_id")
    public Integer getUserId() {
        return userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2370706+08:00", comments="Source field: user_focus.user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2370706+08:00", comments="Source field: user_focus.focused_user_id")
    public Integer getFocusedUserId() {
        return focusedUserId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2370706+08:00", comments="Source field: user_focus.focused_user_id")
    public void setFocusedUserId(Integer focusedUserId) {
        this.focusedUserId = focusedUserId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2380704+08:00", comments="Source field: user_focus.focus_time")
    public Date getFocusTime() {
        return focusTime;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-12-19T23:30:58.2380704+08:00", comments="Source field: user_focus.focus_time")
    public void setFocusTime(Date focusTime) {
        this.focusTime = focusTime;
    }
}