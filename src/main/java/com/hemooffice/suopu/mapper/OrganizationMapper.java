package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.Organization;
import java.util.List;

public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer orgId);

    int insert(Organization record);

    Organization selectByPrimaryKey(Integer orgId);

    List<Organization> selectAll();

    int updateByPrimaryKey(Organization record);
}