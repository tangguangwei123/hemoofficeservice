package com.hemooffice.suopu.dto;

import java.util.Date;

public class Role {
    private Integer roleId;

    private String roleName;

    private Integer orgId;

    private String roleIdentifi;

    private Date createTime;

    private Date modifyTime;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getRoleIdentifi() {
        return roleIdentifi;
    }

    public void setRoleIdentifi(String roleIdentifi) {
        this.roleIdentifi = roleIdentifi == null ? null : roleIdentifi.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}