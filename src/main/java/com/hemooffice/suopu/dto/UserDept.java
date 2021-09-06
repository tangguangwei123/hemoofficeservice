package com.hemooffice.suopu.dto;

public class UserDept {

    private Integer userId;

    private Integer deptId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    @Override
    public String toString() {
        return "UserDept{" +
                "userId=" + userId +
                ", deptId=" + deptId +
                '}';
    }
}
