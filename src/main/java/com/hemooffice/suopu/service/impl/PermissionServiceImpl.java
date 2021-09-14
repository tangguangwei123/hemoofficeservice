package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.Permission;
import com.hemooffice.suopu.dto.Role;
import com.hemooffice.suopu.exception.CusAuthException;
import com.hemooffice.suopu.mapper.PermissionMapper;
import com.hemooffice.suopu.mapper.RoleMapper;
import com.hemooffice.suopu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;

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

    /**
     * 根据角色和机构加载角色Id
     * @param orgId
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> findPermissionListByRoleId(Integer orgId, Integer roleId) {
        return permissionMapper.findPermissionListByRoleId(orgId,roleId);
    }

    /**
     * 加载用户菜单
     * @param orgId
     * @param userId
     * @return
     */
    @Override
    public List<Permission> findUserMenuList(Integer orgId, Integer userId) throws CusAuthException {
        //先加载用户所有角色
        List<Role> roleList = roleMapper.findRoleByUserId(orgId,userId);
        if(roleList.size() == 0){
            throw new CusAuthException("该用户还未授权");
        }
        List<Permission> menuList = new ArrayList<>();
        for (int i = 0; i< roleList.size(); i++){
            menuList.addAll(permissionMapper.findPermissionListByRoleId(orgId,roleList.get(i).getRoleId()));
        }
        //去重

        return null;
    }
}
