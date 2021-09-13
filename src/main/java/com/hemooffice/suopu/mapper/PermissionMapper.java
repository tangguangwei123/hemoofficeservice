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
}