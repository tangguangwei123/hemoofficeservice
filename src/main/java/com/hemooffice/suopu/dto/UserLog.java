package com.hemooffice.suopu.dto;

import java.util.Date;

public class UserLog {
    private Integer logId;

    private Integer userId;

    private String empName;

    private String method;

    private String operaDesc;

    private String ipaddr;

    private Integer operaState;

    private Date operaTime;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getOperaDesc() {
        return operaDesc;
    }

    public void setOperaDesc(String operaDesc) {
        this.operaDesc = operaDesc == null ? null : operaDesc.trim();
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr == null ? null : ipaddr.trim();
    }

    public Integer getOperaState() {
        return operaState;
    }

    public void setOperaState(Integer operaState) {
        this.operaState = operaState;
    }

    public Date getOperaTime() {
        return operaTime;
    }

    public void setOperaTime(Date operaTime) {
        this.operaTime = operaTime;
    }
}