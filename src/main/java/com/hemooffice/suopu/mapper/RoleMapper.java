package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    //加载当前机构所有角色
    public List<Role> findRoleList(@Param("orgId") Integer orgId);
}