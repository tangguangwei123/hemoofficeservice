package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.OaActCategory;
import com.hemooffice.suopu.mapper.ActivitiManageMapper;
import com.hemooffice.suopu.service.ActivitiManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.util.List;

@Service
public class ActivitiManageServiceImpl implements ActivitiManageService {

    @Autowired
    private ActivitiManageMapper activitiManageMapper;
    /**
     * 新增审批类别
     * @param oaActCategory
     * @return
     */
    @Override
    public int addActivitiCategory(OaActCategory oaActCategory) {
        return activitiManageMapper.addActivitiCategory(oaActCategory);
    }

    /**
     *加载当前机构审批类别
     * @param orgId
     * @return
     */
    @Override
    public List<OaActCategory> findOrgActCategory(Integer orgId) {
        return activitiManageMapper.findOrgActCategory(orgId);
    }

    /**
     *删除审批流程类别
     * @param orgId
     * @param id
     * @return
     */
    @Override
    public int deleteActCategory(Integer orgId, Integer id) {
        return activitiManageMapper.deleteActCategory(orgId,id);
    }
}
