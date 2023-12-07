package com.news.NS.common;

public interface CommonConstant {

    Byte USER_ROLE = 1;//普通用户
    Byte PULISHER_ROLE = 2;//新闻编辑
    Byte ADMIN_ROLE= 3;//管理员
    Byte SUPER_ADMIN_ROLE = 4;//超级管理员

    String USER = "1";//鉴权的角色
    String PULISHER = "2";
    String ADMIN = "3";
    String SUPER_ADMIN = "4";

    Byte NEWS_NOTISSUE = 1;//新闻未发布状态
    Byte NEWS_ISSUE = 2;//新闻发布状态
    Byte NEWS_DISABLE = 3;//新闻禁用状态

    Byte RESERVE_TO_BE_REVIEWED = 1;// 新闻待审批状态
    Byte RESERVE_ALREADY_REVIEWED = 2;// 新闻审批通过状态
    Byte RESERVE_CANCELED = 3; // 新闻审批取消状态
    Byte RESERVE_TO_BE_REJECTED = 4; // 新闻审批被驳回
    Byte RESERVE_IS_TIME_OUT = 5; // 新闻审批超过时间未处理

    Byte APPLICATION_NOTPASS = 1;//新闻编辑申请审核状态
    Byte APPLICATION_PASS = 2;//新闻编辑申请通过
    Byte APPLICATION_REFUSE = 3;//新闻编辑申请被拒绝

    Integer MAX_INFO_UPDATE_NUM = 1; // 最大个人信息修改次数，每24小时
}
