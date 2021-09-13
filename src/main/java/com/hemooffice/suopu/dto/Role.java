package com.hemooffice.suopu.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class Role {
    private Integer roleId;
    @Length(max = 30,message = "角色名称长度必须在1-30之间")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    private Integer orgId;

    private String roleIdentifi;

    private Date createTime;

    private Date modifyTime;
    @Length(max = 80,message = "角色描述长度必须在80字符以内")
    private String roleDesc;

    private Integer active;

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

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", orgId=" + orgId +
                ", roleIdentifi='" + roleIdentifi + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", roleDesc='" + roleDesc + '\'' +
                ", active=" + active +
                '}';
    }
}