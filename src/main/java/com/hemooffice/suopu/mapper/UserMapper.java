package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.User;
import java.util.List;

public interface UserMapper {
    /**
     * 新增用户
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 通过主键查询用户
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 加载所有用户
     * @return
     */
    List<User> selectAll();

    /**
     * 根据主键更新用户
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);
}