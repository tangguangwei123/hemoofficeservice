package com.hemooffice.suopu.dto;

import java.util.Date;

public class OaActDef {
    private Integer id;

    private String name;

    private Integer categoryId;

    private Integer orgId;

    private Integer createEmp;

    private Date createDate;

    private Integer active;

    private Integer deleteEmp;

    private Date deleteDate;

    private String formType;

    private String formItem;

    private String flowChart;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(Integer createEmp) {
        this.createEmp = createEmp;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getDeleteEmp() {
        return deleteEmp;
    }

    public void setDeleteEmp(Integer deleteEmp) {
        this.deleteEmp = deleteEmp;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getFormItem() {
        return formItem;
    }

    public void setFormItem(String formItem) {
        this.formItem = formItem == null ? null : formItem.trim();
    }

    public String getFlowChart() {
        return flowChart;
    }

    public void setFlowChart(String flowChart) {
        this.flowChart = flowChart == null ? null : flowChart.trim();
    }
}