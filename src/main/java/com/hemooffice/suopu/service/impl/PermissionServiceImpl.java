package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.Permission;
import com.hemooffice.suopu.mapper.PermissionMapper;
import com.hemooffice.suopu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findPermissionListByRoleId(Integer roleId) {
        return null;
    }

    /**
     * 加载当前机构所有资源权限
     * @param orgId
     * @return
     */
    @Override
    public List<Permission> findPermissionListByOrgId(Integer orgId) {
        return permissionMapper.findPermissionListByOrgId(orgId);
    }
}
