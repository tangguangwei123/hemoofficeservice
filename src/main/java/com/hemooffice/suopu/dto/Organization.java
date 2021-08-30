package com.hemooffice.suopu.dto;

import java.util.Date;

public class Organization {
    private Integer orgId;

    private Integer parentId;

    private String orgName;

    private Integer orderNum;

    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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