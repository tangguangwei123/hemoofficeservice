package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.camundapo.ActReProcdef;
import org.apache.ibatis.annotations.Param;

public interface CamundaMapper {
    /**
     * 根据deploymentId获取流程key
     * @param deploymentId
     * @return
     */
    ActReProcdef getCamKeyByDeploymentId(@Param("deploymentId") String deploymentId);
}
