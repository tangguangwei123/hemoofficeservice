package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.controller.ActivitiManageController;
import com.hemooffice.suopu.service.CamundaService;
import com.hemooffice.suopu.utils.CommonUtils;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CamundaServiceImpl implements CamundaService {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(CamundaServiceImpl.class);
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    /**
     * 部署bpmn。xml资源文件
     */
    public String deploy(String bpmnXml) {
        logger.info("不会没到这吧？");
        logger.info(bpmnXml);

        Deployment deployment = repositoryService.createDeployment()//创建部署对象
                .addString(CommonUtils.GetGUID() +".bpmn",bpmnXml)
                .deploy();//完成部署
        logger.info(deployment.getId());
        return deployment.getId();
    }
    /**
     * 开始一个流程实例
     */
    public void startBpm(String proInsId) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(proInsId);
        String id = processInstance.getId();
        logger.info(id);
    }
    /**
     * 完成一个任务 不带参数
     * @param taskId
     */
    public void comTask(String taskId) {
        taskService.complete(taskId);
    }
    /**
     * 完成一个任务， 并且可以设置变量参数
     * @param taskId
     */
    public void comTask(String taskId, Map map) {
        taskService.complete(taskId, map);
    }
}
