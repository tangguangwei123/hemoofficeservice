package com.hemooffice.suopu.dto;

public class RoleResourceRelationship {

    private Integer roleId;

    private Integer resourceId;

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }
}
