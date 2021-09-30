package com.hemooffice.suopu.dto;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

/**
 * 流程定义参数实体
 */
public class ActivitiDefParam {
    //审批名称
    @NotEmpty(message = "审批单名称不能为空")
    @Length(max = 15, message = "审批单名称长度为1-80")
    private String name;
    //审批单类别
    @NotNull(message = "审批单类别不能为空")
    private Integer category;
    //审批流表单对象
    @Size(min=1,message = "请设计流程表单")
    private JSONArray formItem;
    //流程图定义
    private JSONObject flowChart;

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public void setFormItem(JSONArray formItem) {
        this.formItem = formItem;
    }

    public void setFlowChart(JSONObject flowChart) {
        this.flowChart = flowChart;
    }

    public String getName() {
        return name;
    }

    public Integer getCategory() {
        return category;
    }

    public JSONArray getFormItem() {
        return formItem;
    }

    public JSONObject getFlowChart() {
        return flowChart;
    }

    @Override
    public String toString() {
        return "ActivitiDefParam{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", formItem=" + formItem +
                ", flowChart=" + flowChart +
                '}';
    }
}