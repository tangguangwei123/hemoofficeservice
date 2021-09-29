package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.OaActCategory;
import com.hemooffice.suopu.dto.OaActDef;
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
     * 获取指定机构表单列表
     * @param orgId
     * @return
     */
    List<OaActDef> findActDefFormItem(@Param("orgId") Integer orgId);
}



