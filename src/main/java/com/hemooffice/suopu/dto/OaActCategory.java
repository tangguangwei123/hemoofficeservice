package com.hemooffice.suopu.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class OaActCategory {

    private Integer id;
    @NotNull(message = "审批类别名称不能为空")
    private String categoryName;
    @NotNull(message = "审批排序不能为空")
    private Integer orderNum;
    @NotNull(message = "发起人不可撤销不能为空")
    private Integer irrevocable;

    private Date createDate;

    private Date lastDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}