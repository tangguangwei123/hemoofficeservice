package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.Role;
import org.apache.ibatis.annotations.Param;

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
}