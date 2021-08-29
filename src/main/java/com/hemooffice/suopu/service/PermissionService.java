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
}
