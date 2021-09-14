package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    /**
     * 加载当前机构所有资源权限
     * @param orgId
     * @return
     */
    List<Permission> findPermissionListByOrgId(@Param("orgId") Integer orgId);

    /**
     *根据角色和机构加载角色Id
     * @param roleId
     * @return
     */
    List<Permission> findPermissionListByRoleId(@Param("orgId") Integer integer, @Param("roleId") Integer roleId);
}