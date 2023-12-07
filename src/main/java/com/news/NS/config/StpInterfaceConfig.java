package com.news.NS.config;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.news.NS.domain.User;
import com.news.NS.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StpInterfaceConfig implements StpInterface {

    private final UserMapper userMapper;

    public StpInterfaceConfig(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 存储的loginId即为用户id
        Integer userId = Integer.parseInt(loginId.toString());
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        return session.get("identification", () -> {
            Optional<User> optionalUser = userMapper.selectByPrimaryKey(userId);
            List<String> roleNameList = new ArrayList<>();
            if (optionalUser.isPresent()) {
                Byte identification = optionalUser.get().getIdentification();
                roleNameList.add(String.valueOf(identification));
            }
            return roleNameList;
        });
    }
}
