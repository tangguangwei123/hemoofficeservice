package com.hemooffice.suopu.dto;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OaActCategory {

    private Integer id;

    private Integer orgId;
    @NotNull(message = "审批类别名称不能为空")
    private String categoryName;
    @NotNull(message = "审批排序不能为空")
    private Integer orderNum;
    @NotNull(message = "发起人不可撤销不能为空")

    private Integer irrevocable;

    private String createDate;

    private String lastDate;

    private Integer active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getIrrevocable() {
        return irrevocable;
    }

    public void setIrrevocable(Integer irrevocable) {
        this.irrevocable = irrevocable;
    }

    public String getCreateDate() { return createDate; }

    public void setCreateDate(Date createDate)
    {
        if(createDate == null){
            this.createDate = null;
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(createDate);
        this.createDate = dateString;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        if(lastDate == null){
            this.lastDate = null;
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(lastDate);
        this.lastDate = dateString;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getActive() {
        return active;
    }
}