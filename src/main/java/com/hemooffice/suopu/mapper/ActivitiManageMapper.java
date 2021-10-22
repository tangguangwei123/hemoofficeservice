package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.OaActCategory;
import com.hemooffice.suopu.dto.OaActDef;
import com.hemooffice.suopu.dto.OaActDefRes;
import com.hemooffice.suopu.dto.OaActFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * activiti相关管理
 */
public interface ActivitiManageMapper {
    /**
     * 新增审批类别
     * @throws Exception
     */
    int addActivitiCategory(OaActCategory oaActCategory);

    /**
     * 加载当前机构审批类别
     * @param orgId
     * @return
     */
    List<OaActCategory> findOrgActCategory(@Param("orgId") Integer orgId);

    /**
     * 删除审批流程类别
     * @return
     */
    int deleteActCategory(@Param("orgId") Integer orgId, @Param("id") Integer id);

    /**
     * 插入流程定义
     * @param
     * @return
     */
    int addActDef(OaActDef oaActDef);

    /**
     * 获取指定机构流程列表
     * @param orgId
     * @return
     */
    List<OaActDefRes> findActDefList(@Param("orgId") Integer orgId);

    /**
     * 获取指定机构表单列表
     * @param orgId
     * @return
     */
    List<OaActDef> findActDefFormItem(@Param("orgId") Integer orgId);

    /**
     * 启用或者停用流程
     * @param orgId
     * @param id
     * @return
     */
    int updateActDefActive(@Param("orgId") Integer orgId,@Param("id") Integer id, @Param("active") Integer active);

    /**
     * 根据流程ID加载表单
     * @param orgId
     * @param id
     * @return
     */
    List<OaActDef> findActDefFormItemById(@Param("orgId") Integer orgId,@Param("id") Integer id);

    /**
     * 根据流程ID加载Bpmn
     * @param orgId
     * @param id
     * @return
     */
    List<OaActDef> findActDefBpmn(@Param("orgId") Integer orgId,@Param("id") Integer id);
    /**
     * 更新流程定义根据流程ID
     * @param oaActDef
     * @return
     */
    int updateActDefById(OaActDef oaActDef);

    /**
     * 根据ID加载流程定义
     * @param orgId
     * @param id
     * @return
     */
    OaActDef findActDefById(@Param("orgId") Integer orgId,@Param("id") Integer id);

    /**
     * 插入流程附件
     * @param oaActFile
     * @return
     */
    int insertOaActFile(OaActFile oaActFile);

    /**
     * 删除流程附件
     * @param fileId
     * @return
     */
    int removeOaActFile(@Param("orgId") Integer orgId, @Param("fileId") Integer fileId);
}



