package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.OaActCategory;

import java.util.List;

public interface ActivitiManageService {
    /**
     * 新增审批类别
     * @param oaActCategory
     * @return
     */
    int addActivitiCategory(OaActCategory oaActCategory);

    /**
     * 加载当前机构审批类别
     * @param orgId
     * @return
     */
    List<OaActCategory> findOrgActCategory(Integer orgId);

    /**
     * 删除审批流程类别
     * @param orgId
     * @param id
     * @return
     */
    int deleteActCategory(Integer orgId,Integer id);
}
