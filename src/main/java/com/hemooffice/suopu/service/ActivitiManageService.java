package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.*;
import com.hemooffice.suopu.exception.CusAuthException;
import com.hemooffice.suopu.exception.CusSystemException;
import org.camunda.feel.syntaxtree.In;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 更新流程定义bpmn定义
     * @param oaActDef
     * @return
     * @throws CusAuthException
     */
    int updateActDefBpmn(OaActDef oaActDef) throws CusAuthException;

    /**
     * 根据ID加载流程定义
     * @param orgId
     * @param id
     * @return
     */
    OaActDef findActDefById(Integer orgId,Integer id);

    /**
     * 插入流程附件
     * @param file
     * @return
     */
    int insertOaActFile(Integer orgId, Integer bpmnId, String elementId, MultipartFile file, User user) throws CusAuthException, CusSystemException;

    /**
     *删除流程附件
     * @param orgId
     * @param fileId
     * @return
     */
    int removeOaActFile(Integer orgId, Integer fileId);
    /**
     *根据机构id、流程id、附件id查询附件列表
     * @param orgId
     * @return
     */
    List<OaActFile> findOaActFileListByFileIdOrgIdActId(Integer orgId, Integer actId);
    /**
     * 根据机构id、流程id、附件id查询附件blob内容
     * @param orgId
     * @param actId
     * @return
     */
    OaActFile findFileContentByFileIdOrgIdActId(Integer orgId, Integer actId, Integer fileId);

    /**
     * 发起审批
     * @param orgId
     * @param deploymentId
     * @return
     */
    String startHandleApproval(Integer orgId, String deploymentId) throws CusAuthException;
}
