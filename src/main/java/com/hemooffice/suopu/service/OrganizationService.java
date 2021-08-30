package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.Organization;

public interface OrganizationService {

    public Organization findOrganizationByUserId(Integer userId);
}
