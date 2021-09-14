package com.hemooffice.suopu.dto;

public class RoleUserRelationship {

    private Integer userId;

    private Integer roleId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getRoleId() {
        return roleId;
    }
}
