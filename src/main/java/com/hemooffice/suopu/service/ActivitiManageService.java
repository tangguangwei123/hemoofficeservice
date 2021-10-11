package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.OaActCategory;
import com.hemooffice.suopu.dto.OaActDef;
import com.hemooffice.suopu.dto.OaActDefRes;

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

    /**
     * 插入流程定义
     * @param oaActDef
     * @return
     */
    int addActDef(OaActDef oaActDef);

    /**
     * 获取指定机构流程列表
     * @param orgId
     * @return
     */
    List<OaActDefRes> findActDefList(Integer orgId);

    /**
     * 获取指定机构表单列表
     * @param orgId
     * @return
     */
    List<OaActDef> findActDefFormItem(Integer orgId);
}
