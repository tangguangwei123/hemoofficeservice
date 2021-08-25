package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.OaActCategory;

import java.util.Map;

/**
 * activiti相关管理
 */
public interface ActivitiManageMapper {
    /**
     * 新增审批分类
     * @throws Exception
     */
    void addActivitiCategory(OaActCategory oaActCategory) throws Exception;
}



