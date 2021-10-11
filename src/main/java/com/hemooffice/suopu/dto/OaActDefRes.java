package com.hemooffice.suopu.dto;

public class OaActDefRes extends OaActDef{
    //创建员工姓名
    private String createEmpName;
    //分类名称
    private String categoryName;

    public void setCreateEmpName(String createEmpName) {
        this.createEmpName = createEmpName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreateEmpName() {
        return createEmpName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
