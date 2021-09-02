package com.hemooffice.suopu.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Organization implements Serializable {
    private Integer orgId;

    private Integer parentId;

    private String orgName;

    private Integer orderNum;

    private String createTime;

    private Integer active;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(createTime);
        this.createTime = dateString;
    }

    public Integer getActive(){ return active; }

    public void setActive(Integer active){ this.active = active;}

    @Override
    public String toString() {
        return "Organization{" +
                "orgId=" + orgId +
                ", parentId=" + parentId +
                ", orgName='" + orgName + '\'' +
                ", orderNum=" + orderNum +
                ", createTime=" + createTime +
                ", active=" + active +
                '}';
    }
}