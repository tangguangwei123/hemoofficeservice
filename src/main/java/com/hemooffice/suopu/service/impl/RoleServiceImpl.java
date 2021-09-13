package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.Role;
import com.hemooffice.suopu.exception.CusAuthException;
import com.hemooffice.suopu.exception.CusSystemException;
import com.hemooffice.suopu.mapper.RoleMapper;
import com.hemooffice.suopu.service.RoleService;
import com.hemooffice.suopu.utils.CommonUtils;
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

    /**
     * 新增机构角色
     * @param role
     * @return
     */
    @Override
    public int addOrgRole(Role role) {
        //如果id不为null则更新，否则插入
        if(role.getRoleId() == null){
            return roleMapper.addOrgRole(role);
        }else {
            return roleMapper.updateRole(role);
        }
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @Override
    public int deleteRole(Integer orgId, Integer roleId) throws CusAuthException {
        //删除角色
        //根据id查到角色信息
        Role role = roleMapper.findRoleByRoleId(orgId,roleId);

        if(role == null){
            throw new CusAuthException("角色ID为空!");
        }
        role.setActive(0);
        //更新角色
        return roleMapper.updateRole(role);
    }
}
