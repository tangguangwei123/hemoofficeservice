package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 根据账户名查询用户
     * @param userAccount
     * @return
     */
    User findUserByUserAccount(String userAccount);

    /**
     * 根据机构id获取机构所有用户
     * @param orgId
     * @return
     */
    List<User> findUsersByOrgId(@Param("orgId") Integer orgId);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据角色Id加载用户列表
     * @param orgId
     * @param roleId
     * @return
     */
    List<User> findUserListByRoleId(@Param("orgId") Integer orgId, @Param("roleId") Integer roleId);
}