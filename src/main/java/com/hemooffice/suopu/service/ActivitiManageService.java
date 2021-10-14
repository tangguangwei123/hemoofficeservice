package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.OaActCategory;
import com.hemooffice.suopu.dto.OaActDef;
import com.hemooffice.suopu.dto.OaActDefRes;
import com.hemooffice.suopu.exception.CusAuthException;
import org.camunda.feel.syntaxtree.In;

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

    /**
     * 启用或者停用流程
     * @param orgId
     * @param id
     * @param active
     * @return
     */
    int updateActDefActive(Integer orgId, Integer id,Integer active) throws CusAuthException;
    /**
     * 根据流程ID加载表单
     * @param orgId
     * @param id
     * @return
     */
    List<OaActDef> findActDefFormItemById(Integer orgId, Integer id);

    /**
     * 根据流程ID加载Bpmn
     * @param orgId
     * @param id
     * @return
     */
    List<OaActDef> findActDefBpmn(Integer orgId, Integer id);
    /**
     * 更新流程定义表单定义
     * @param oaActDef
     * @return
     */
    int updateActDefForm(OaActDef oaActDef) throws CusAuthException;
}
