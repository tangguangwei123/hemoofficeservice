package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.Role;
import java.util.List;

public interface RoleMapper {

    /**
     * 新增角色
     * @param record
     * @return
     */
    int insert(Role record);

    /**
     * 根据角色ID加载角色
     * @param roleId
     * @return
     */
    Role selectByPrimaryKey(Integer roleId);

    /**
     * 加载所有角色
     * @return
     */
    List<Role> selectAll();

    /**
     * 根据角色ID更新角色
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);
}