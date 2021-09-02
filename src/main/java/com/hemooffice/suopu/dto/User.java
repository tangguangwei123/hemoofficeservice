package com.hemooffice.suopu.dto;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer userId;

    private String userName;

    private String sex;

    private String userAccount;

    private String loginAlias;

    private String empNbm;

    private String password;

    private String signature;

    private String email;

    private String mobile;

    private String tell;

    private Integer hiddenTell;

    private Integer orderNum;

    private Integer orgId;

    private Integer status;

    private Date createTime;

    private Date modifyTime;

    private Date lastLoginTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getUserAccount() {
        return userAccount.toUpperCase();
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim().toUpperCase();
    }

    public String getLoginAlias(){return loginAlias; }

    public void setLoginAlias(String loginAlias) {this.loginAlias = loginAlias == null ? null : loginAlias.trim(); }

    public String getEmpNbm(){ return empNbm; }

    public void setEmpNbm(String empNbm) {this.empNbm = empNbm == null ? null : empNbm.trim(); }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSignature() {return signature; }

    public void setSignature(String signature) { this.signature = signature == null ? null : signature.trim(); }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell == null ? null : tell.trim();
    }

    public Integer getHiddenTell() {
        return hiddenTell;
    }

    public void setHiddenTell(Integer hiddenTell) {
        this.hiddenTell = hiddenTell;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", password='" + password + '\'' +
                ", signature='" + signature + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", tell='" + tell + '\'' +
                ", hiddenTell=" + hiddenTell +
                ", orderNum=" + orderNum +
                ", orgId=" + orgId +
                ", status=" + status +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}