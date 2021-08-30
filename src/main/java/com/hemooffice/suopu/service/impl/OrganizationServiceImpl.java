package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.Organization;
import com.hemooffice.suopu.mapper.OrganizationMapper;
import com.hemooffice.suopu.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    /**
     * 根据userId查询机构信息
     * @param userId
     * @return
     */
    @Override
    public Organization findOrganizationByUserId(Integer userId) {
        return organizationMapper.findOrganizationByUserId(userId);
    }
}
