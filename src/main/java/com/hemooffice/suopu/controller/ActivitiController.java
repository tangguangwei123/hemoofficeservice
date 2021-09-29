package com.hemooffice.suopu.controller;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class ActivitiController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private HistoryService historyService;

    @GetMapping("/hello")
    public String hello(){
        logger.info("日志系统搭建成功");
        return "hello-ok"+ new Date();
    }

    @GetMapping("/deploy")
    public String deploy(){
        //得到流程引擎的方式三，利用底层封装，来加载配置文件，只需要调用方法即可
        //ProcessEngine pec = ProcessEngines.getDefaultProcessEngine();

        //部署的服务对象
        //RepositoryService repositoryService = processEngine.getRepositoryService();

        //部署请假任务
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/demo1.bpmn")
                .name("请假")
                .deploy();

        System.out.println("请假部署ID：" + deploy.getId());

        return "deploy-ok"+ new Date();
    }

    @GetMapping("/start")
    public String start(){
        runtimeService.startProcessInstanceById("myProcess_1:1:4");

        return "start-ok"+ new Date();
    }

    @GetMapping("/complete")
    public String complete(){
        taskService.complete("10011");

        return "complete-ok"+ new Date();
    }
}
