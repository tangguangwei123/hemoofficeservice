package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.Permission;
import java.util.List;

public interface PermissionMapper {
    /**
     * 根据资源ID加载资源
     * @param id
     * @return
     */
    Permission selectByPrimaryKey(Integer id);

    /**
     * 加载所有资源
     * @return
     */
    List<Permission> selectAll();

    /**
     * 根据资源ID更新资源
     * @param record
     * @return
     */
    int updateByPrimaryKey(Permission record);
}