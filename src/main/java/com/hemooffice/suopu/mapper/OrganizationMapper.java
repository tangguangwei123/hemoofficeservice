package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.Organization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrganizationMapper {
    /**
     * 根据userId查询机构信息
     * @param userId
     * @return
     */
    Organization findOrganizationByUserId(@Param("userId") Integer userId);
}