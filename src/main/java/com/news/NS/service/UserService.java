package com.news.NS.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.news.NS.domain.User;
import com.news.NS.domain.dto.UserLoginDTO;
import com.news.NS.mapper.UserDynamicSqlSupport;
import com.news.NS.mapper.UserMapper;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //    @Autowired
//    private RedisCacheUtil<String> redisCacheUtil;
//
//    @Autowired
//    private UserRoleService userRoleService;
//
//    @Autowired
//    private UserRoleMapper userRoleMapper;
    public Map<String, Object> login(UserLoginDTO userLoginDTO) {
        String pwd = SaSecureUtil.md5(userLoginDTO.getPwd());
        SelectStatementProvider queryStatement = select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.account, isEqualTo(userLoginDTO.getAcu()))
                .and(UserDynamicSqlSupport.password, isEqualTo(pwd))
                .build().render(RenderingStrategies.MYBATIS3);
        Optional<User> userOptional = userMapper.selectOne(queryStatement);
        User user;
        Map<String, Object> map = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            map = new HashMap<>();
            StpUtil.login(user.getUserId());
            String token = StpUtil.getTokenValueByLoginId(StpUtil.getLoginId());
            StpUtil.getSessionByLoginId(StpUtil.getLoginId()).set("userId", user.getUserId());
            map.put("user", user);
            map.put("token", token);
        }
        return map;
    }
}
