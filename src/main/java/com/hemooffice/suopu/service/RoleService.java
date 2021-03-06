package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.Role;
import com.hemooffice.suopu.exception.CusAuthException;

import java.util.List;

public interface RoleService {

    /**
     * 根据userId查询用户角色列表
     * @param userId
     * @return
     */
    List<Role> findRoleListByUserId(Integer userId);

    /**
     * 加载当前机构所有角色
     * @param orgId
     * @return
     */
    List<Role> findRoleList(Integer orgId);

    /**
     * 新增机构角色
     * @param role
     * @return
     */
    int addOrgRole(Role role);

    /**
     * 删除角色
     * @return
     */
    int deleteRole(Integer orgId, Integer roleId) throws CusAuthException;
}
