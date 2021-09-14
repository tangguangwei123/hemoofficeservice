package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.Permission;
import com.hemooffice.suopu.exception.CusAuthException;

import java.util.List;

public interface PermissionService {

    /**
     * 加载指定角色权限
     * @param roleId
     * @return
     */
    List<Permission> findPermissionListByRoleId(Integer roleId);

    /**
     *加载当前机构所有资源权限
     * @param orgId
     * @return
     */
    List<Permission> findPermissionListByOrgId(Integer orgId);

    /**
     * 根据角色和机构加载角色Id
     * @param orgId
     * @param roleId
     * @return
     */
    List<Permission> findPermissionListByRoleId(Integer orgId, Integer roleId);

    /**
     *加载用户菜单
     * @return
     */
    List<Permission> findUserMenuList(Integer orgId, Integer userId) throws CusAuthException;
}
