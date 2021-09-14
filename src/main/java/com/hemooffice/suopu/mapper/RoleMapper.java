package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.Role;
import com.hemooffice.suopu.dto.RoleResourceRelationship;
import com.hemooffice.suopu.dto.RoleUserRelationship;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

public interface RoleMapper {

    /**
     * 加载当前机构所有角色
     * @param orgId
     * @return
     */
    public List<Role> findRoleList(@Param("orgId") Integer orgId);

    /**
     *新增机构角色
     * @param role
     * @return
     */
    public int addOrgRole(Role role);

    /**
     * 根据标识符查找角色
     * @return
     */
    public Role findRoleByRoleIdentifi(@Param("orgId") Integer orgId, @Param("idenrifi") String idenrifi);

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    public int updateRole(Role role);

    /**
     * 根据角色ID查找角色
     * @param roleId
     * @return
     */
    public Role findRoleByRoleId(@Param("orgId") Integer orgId, @Param("roleId") Integer roleId);

    /**
     * 删除角色用户关联关系
     * @param orgId
     * @param roleId
     * @return
     */
    public int deleteRoleUser(@Param("orgId") Integer orgId,@Param("roleId") Integer roleId);

    /**
     *
     * 删除角色资源关联关系
     * @param orgId
     * @param roleId
     * @return
     */
    public int deleteRolePermission(@Param("orgId") Integer orgId, @Param("roleId") Integer roleId);

    /**
     * 添加角色用户关联关系
     * @param roleUserRelationshipList
     * @return
     */
    public int addRoleUsers(@Param("roleUserRelationshipList") List<RoleUserRelationship> roleUserRelationshipList);

    /**
     * 添加角色资源关联
     * @param roleResourceRelationshipList
     * @return
     */
    public int addRoleResource(@Param("roleResourceRelationshipList") List<RoleResourceRelationship> roleResourceRelationshipList);

    /**
     * 根据机构id用户id查找角色列表
     * @param orgId
     * @param userId
     * @return
     */
    public List<Role> findRoleByUserId(@Param("orgId") Integer orgId, @Param("userId") Integer userId);
}