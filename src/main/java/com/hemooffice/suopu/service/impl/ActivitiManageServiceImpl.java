package com.hemooffice.suopu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hemooffice.suopu.dto.OaActCategory;
import com.hemooffice.suopu.dto.OaActDef;
import com.hemooffice.suopu.dto.OaActDefRes;
import com.hemooffice.suopu.exception.CusAuthException;
import com.hemooffice.suopu.mapper.ActivitiManageMapper;
import com.hemooffice.suopu.service.ActivitiManageService;
import com.hemooffice.suopu.service.CamundaService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.dc.pr.PRError;

import java.util.List;

@Service
public class ActivitiManageServiceImpl implements ActivitiManageService {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(ActivitiManageServiceImpl.class);
    @Autowired
    private ActivitiManageMapper activitiManageMapper;
    @Autowired
    private CamundaService camundaService;
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

    /**
     * 插入流程定义
     * @param oaActDef
     * @return
     */
    @Override
    @Transactional(rollbackFor={Exception.class})
    public int addActDef(OaActDef oaActDef) {
        //部署流程
        JSONObject jsonObject = JSONObject.parseObject(oaActDef.getFlowChart());
        camundaService.deploy(jsonObject.get("xml").toString());
        return activitiManageMapper.addActDef(oaActDef);
    }

    /**
     * 获取指定机构流程列表
     * @param orgId
     * @return
     */
    @Override
    public List<OaActDefRes> findActDefList(Integer orgId) {
        return activitiManageMapper.findActDefList(orgId);
    }

    /**
     * 获取指定机构表单列表
     * @param orgId
     * @return
     */
    @Override
    public List<OaActDef> findActDefFormItem(Integer orgId) {
        return activitiManageMapper.findActDefFormItem(orgId);
    }

    /**
     * 启用或者停用流程
     * @param orgId
     * @param id
     * @param active
     * @return
     */
    @Override
    public int updateActDefActive(Integer orgId, Integer id, Integer active) throws CusAuthException {
        if(id == null){
            throw new CusAuthException("流程ID为空！");
        }
        if(active == null){
            throw new CusAuthException("active为空！");
        }
        return activitiManageMapper.updateActDefActive(orgId,id,active);
    }

    /**
     * 根据流程ID加载表单
     * @param orgId
     * @param id
     * @return
     */
    @Override
    public List<OaActDef> findActDefFormItemById(Integer orgId, Integer id) {
        return activitiManageMapper.findActDefFormItemById(orgId,id);
    }

    /**
     * 根据流程ID加载Bpmn
     * @param orgId
     * @param id
     * @return
     */
    @Override
    public List<OaActDef> findActDefBpmn(Integer orgId, Integer id) {
        return activitiManageMapper.findActDefBpmn(orgId,id);
    }

    /**
     * 更新流程定义表单定义
     * @param oaActDef
     * @return
     */
    @Override
    public int updateActDefForm(OaActDef oaActDef) throws CusAuthException {
        //根据id查询数据库次流程定义内容
        OaActDef dOaActDef = activitiManageMapper.findActDefById(oaActDef.getOrgId(),oaActDef.getId());
        if(dOaActDef == null){
            throw new CusAuthException("不存在此流程定义！");
        }
        dOaActDef.setFormItem(oaActDef.getFormItem());
        return activitiManageMapper.updateActDefById(dOaActDef);
    }
}
