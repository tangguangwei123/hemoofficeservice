package com.hemooffice.suopu.controller;

import com.hemooffice.suopu.dto.ActivitiDefParam;
import com.hemooffice.suopu.dto.Msg;
import com.hemooffice.suopu.dto.OaActCategory;
import com.hemooffice.suopu.dto.Organization;
import com.hemooffice.suopu.service.ActivitiManageService;
import com.hemooffice.suopu.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/api/act/")
public class ActivitiManageController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(ActivitiManageController.class);
    @Autowired
    private ActivitiManageService activitiManageService;
    @Autowired
    private SessionUtil sessionUtil;

    @PostMapping("/add-category")
    public Msg addActCategory(@Validated @RequestBody OaActCategory oaActCategory){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        oaActCategory.setOrgId(organization.getOrgId());
        return Msg.success(activitiManageService.addActivitiCategory(oaActCategory));
    }

    /**
     * 加载当前机构审批类别
     * @return
     */
    @GetMapping("/catagory-list")
    public Msg findOrgActCategory(){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        return Msg.success(activitiManageService.findOrgActCategory(organization.getOrgId()));
    }

    /**
     * 删除审批流程类别
     * @return
     */
    @GetMapping("/category-delete")
    public Msg deleteActCategory(@NotNull(message = "审批类别id不能为空") @RequestParam("id") Integer id){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        return Msg.success(activitiManageService.deleteActCategory(organization.getOrgId(),id));
    }

    /**
     *新增流程定义
     * @return
     */
    @PostMapping("/activitidef-add")
    public Msg addActivitiDef(@Validated @RequestBody ActivitiDefParam activitiDefParam){

        logger.info("流程定义数据：");
        logger.info(activitiDefParam.toString());

        return Msg.success("ok");
    }
}
