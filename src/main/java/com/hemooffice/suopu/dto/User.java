package com.hemooffice.suopu.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

//参考文档地址: https://www.jianshu.com/p/67d3637493c7
public class User implements Serializable {
    private Integer userId;
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z]+$",message = "姓名必须是汉字或字母组成")
    @Length(min=2,max=15, message = "姓名长度在2-15")
    private String userName;
    @NotNull(message = "性别不能为空")
    private String sex;
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,20}$",message = "登录账户必须6-20长度的字母数字或下划线组成")
    private String userAccount;
    @Length(max = 15, message = "登录别名长度为1-15")
    private String loginAlias;
    @Length(max = 15, message = "员工编号长度为1-15")
    private String empNbm;

    private String password;
    @Length(max=80, message = "签名不能多余80个字符")
    private String signature;

    private String email;
    @Pattern(regexp = "^1[34578]\\d{9}$",message = "手机号码无效")
    private String mobile;
    @Pattern(regexp = "^[0-9]*$", message = "电话号码必须是数字")
    @Length(min = 5, max=12,message = "电话号码长度在5-12之间")
    private String tell;

    private Integer hiddenTell;
    @Max(value = 10000,message = "序号超出最大值")
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
        return userAccount == null ? null : userAccount.toUpperCase();
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