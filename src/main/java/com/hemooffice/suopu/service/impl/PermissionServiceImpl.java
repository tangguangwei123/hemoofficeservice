package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.Permission;
import com.hemooffice.suopu.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<Permission> findPermissionListByRoleId(Integer roleId) {
        return null;
    }
}
