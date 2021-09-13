package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.Permission;

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
}
