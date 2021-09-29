package com.hemooffice.suopu.service;

public interface ActivitiService {
    /**
     * 部署流程返回ID
     * @return
     */
    String deploy(String bpmnXml);
}
