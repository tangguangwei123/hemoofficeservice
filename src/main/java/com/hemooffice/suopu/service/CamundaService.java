package com.hemooffice.suopu.service;

import java.util.Map;

public interface CamundaService {
    /**
     * 部署bpmn。xml资源文件
     * @return
     */
    String deploy(String bpmnXml, String name);

    /**
     * 开始一个流程实例
     * @param proInsId
     */
    String startProcessInstance(String proInsId);

    /**
     * 完成一个任务 不带参数
     * @param taskId
     */
    void comTask(String taskId);

    /**
     * 完成一个任务， 并且可以设置变量参数
     * @param taskId
     * @param map
     */
    void comTask(String taskId, Map map);
}
