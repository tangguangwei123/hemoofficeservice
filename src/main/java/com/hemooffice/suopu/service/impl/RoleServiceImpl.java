package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.Role;
import com.hemooffice.suopu.mapper.RoleMapper;
import com.hemooffice.suopu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findRoleListByUserId(Integer userId) {
        return null;
    }

    /**
     * 加载当前机构所有角色
     * @param orgId
     * @return
     */
    @Override
    public List<Role> findRoleList(Integer orgId) {
        return roleMapper.findRoleList(orgId);
    }
}
