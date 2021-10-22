package com.hemooffice.suopu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hemooffice.suopu.dto.*;
import com.hemooffice.suopu.exception.CusAuthException;
import com.hemooffice.suopu.exception.CusSystemException;
import com.hemooffice.suopu.mapper.ActivitiManageMapper;
import com.hemooffice.suopu.service.ActivitiManageService;
import com.hemooffice.suopu.service.CamundaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    /**
     * 更新流程定义bpmn定义
     * @param oaActDef
     * @return
     * @throws CusAuthException
     */
    @Override
    public int updateActDefBpmn(OaActDef oaActDef) throws CusAuthException {
        //根据id查询数据库次流程定义内容
        OaActDef dOaActDef = activitiManageMapper.findActDefById(oaActDef.getOrgId(),oaActDef.getId());
        if(dOaActDef == null){
            throw new CusAuthException("不存在此流程定义！");
        }
        dOaActDef.setFlowChart(oaActDef.getFlowChart());
        return activitiManageMapper.updateActDefById(dOaActDef);
    }

    /**
     * 根据ID加载流程定义
     * @param orgId
     * @param id
     * @return
     */
    @Override
    public OaActDef findActDefById(Integer orgId, Integer id) {
        return activitiManageMapper.findActDefById(orgId,id);
    }

    /**
     * 插入流程附件
     * @param file
     * @return
     */
    @Override
    public int insertOaActFile(Integer orgId, Integer bpmnId, String elementId, MultipartFile file, User user) throws CusAuthException, CusSystemException {
        if (file.isEmpty()) {
            throw new CusAuthException("文件不能为空");
        }
        // 获取文件名
        String fileOriginalName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
        // 解决中文问题，liunx下中文路径，图片显示问题
        String fileName = UUID.randomUUID() + suffixName;
        //封装参数
        OaActFile oaActFile = new OaActFile();
        oaActFile.setFileName(fileName);
        oaActFile.setOrgId(orgId);
        oaActFile.setFileOriginalName(fileOriginalName);
        oaActFile.setBpmnId(bpmnId);
        oaActFile.setElementId(elementId);
        oaActFile.setUploadUser(user.getUserId());
        try {
            oaActFile.setFileContent(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new CusSystemException("读取附件内容失败");
        }
        oaActFile.setActive(1);

        activitiManageMapper.insertOaActFile(oaActFile);

        return oaActFile.getFileId();
    }

    /**
     * 删除流程附件
     * @param orgId
     * @param fileId
     * @return
     */
    @Override
    public int removeOaActFile(Integer orgId, Integer fileId) {
        //删除此附件
        return activitiManageMapper.removeOaActFile(orgId,fileId);
    }
}
