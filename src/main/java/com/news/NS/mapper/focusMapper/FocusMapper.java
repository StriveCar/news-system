package com.news.NS.mapper.focusMapper;

import com.news.NS.domain.dto.UserInteract.UserFocusDTO;
import com.news.NS.domain.vo.FocusVo;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FocusMapper {
    @Insert("insert into user_focus(user_id, focused_user_id) VALUES (#{userId},#{focusedUserId})")
    int focusUser(UserFocusDTO userFocusDTO);

    @Select("select * from user_focus where user_id=#{userId} and focused_user_id=#{focusedUserId}")
    UserFocusDTO getOneFocusInfo(UserFocusDTO userFocusDTO);

    @Select("select focused_user_id from user_focus where user_id=#{userId}")
    List<Integer> getFocusIdList(Integer userId);

    @Select("select user_id from user_focus where focused_user_id=#{focusedUserId}")
    List<Integer> getFollowsIdList(Integer focusedUserId);

    @Select("select * from user_focus where focused_user_id=#{focusedUserId}")
    List<UserFocusDTO> getFollwList(Integer focusedUserId);

    @Delete("delete from user_focus where user_id=#{userId} and focused_user_id=#{focusedUserId}")
    int unfocusUser(UserFocusDTO userFocusDTO);

    /**
     * 根据用户id列表 查询用户id,昵称，头像
     * @param ids  被关注的用户id
     * @return
     */
    List<FocusVo> getFocusVoList(List<Integer> ids);


}
