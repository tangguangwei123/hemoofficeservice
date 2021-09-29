package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.controller.ActivitiController;
import com.hemooffice.suopu.service.ActivitiService;
import com.hemooffice.suopu.utils.CommonUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActivitiServiceImpl implements ActivitiService {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(ActivitiServiceImpl.class);

    @Autowired
    private RepositoryService repositoryService;
    /**
     * 部署流程返回ID
     * @param bpmnXml
     * @return
     */
    @Override
    public String deploy(String bpmnXml) {
        //部署请假任务
        Deployment deployment = repositoryService.createDeployment()//创建部署对象
                .addString(CommonUtils.GetGUID() +".bpmn",bpmnXml)
                .deploy();//完成部署
        logger.info("部署ID：" + deployment.getId());
        return deployment.getId();
    }
}
