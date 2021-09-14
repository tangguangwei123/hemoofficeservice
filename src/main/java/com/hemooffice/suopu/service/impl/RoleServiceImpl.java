package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.Role;
import com.hemooffice.suopu.dto.RoleResourceRelationship;
import com.hemooffice.suopu.dto.RoleUserRelationship;
import com.hemooffice.suopu.exception.CusAuthException;
import com.hemooffice.suopu.exception.CusSystemException;
import com.hemooffice.suopu.mapper.RoleMapper;
import com.hemooffice.suopu.service.RoleService;
import com.hemooffice.suopu.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Transactional(rollbackFor={Exception.class})
    public int addOrgRole(Role role) {
        //如果id不为null则更新，否则插入
        if(role.getRoleId() == null){
            roleMapper.addOrgRole(role);
        }else {
            //删除角色用户关联关系
            roleMapper.deleteRoleUser(role.getOrgId(),role.getRoleId());
            //删除角色资源关联
            roleMapper.deleteRolePermission(role.getOrgId(),role.getRoleId());

            roleMapper.updateRole(role);
        }

        if(role.getUserList().size() > 0){
            //添加角色用户关联关系
            List<RoleUserRelationship> roleUserRelationshipList = new ArrayList<>();

            for (int i = 0; i < role.getUserList().size(); i++){
                RoleUserRelationship roleUserRelationship = new RoleUserRelationship();
                roleUserRelationship.setUserId(role.getUserList().get(i));
                roleUserRelationship.setRoleId(role.getRoleId());
                roleUserRelationshipList.add(roleUserRelationship);
            }
            roleMapper.addRoleUsers(roleUserRelationshipList);
        }

        if(role.getPermissionList().size() > 0){
            //添加角色资源关联
            List<RoleResourceRelationship> roleResourceRelationshipList = new ArrayList<>();
            for (int k = 0; k < role.getPermissionList().size(); k++){
                RoleResourceRelationship resourceRelationship = new RoleResourceRelationship();
                resourceRelationship.setResourceId(role.getPermissionList().get(k));
                resourceRelationship.setRoleId(role.getRoleId());
                roleResourceRelationshipList.add(resourceRelationship);
            }
            roleMapper.addRoleResource(roleResourceRelationshipList);
        }

        return 1;
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
