package com.news.NS.mapper.focusMapper;

import com.news.NS.domain.dto.UserInteract.UserFocusDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FocusMapper {
    @Insert("insert into user_focus(user_id, focused_user_id) VALUES (#{userId},#{focusedUserId})")
    int focusUser(UserFocusDTO userFocusDTO);

    @Select("select * from user_focus where user_id=#{userId} and focused_user_id=#{focusedUserId}")
    UserFocusDTO getOneFocusInfo(UserFocusDTO userFocusDTO);

    @Select("select * from user_focus where user_id=#{userId}")
    List<UserFocusDTO> getFocusList(Integer userId);

    @Select("select * from user_focus where focused_user_id=#{focusedUserId}")
    List<UserFocusDTO> getFollwList(Integer focusedUserId);

    @Delete("delete from user_focus where user_id=#{userId} and focused_user_id=#{focusedUserId}")
    int unfocusUser(UserFocusDTO userFocusDTO);
}
