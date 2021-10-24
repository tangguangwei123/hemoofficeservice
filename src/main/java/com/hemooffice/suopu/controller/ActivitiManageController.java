package com.hemooffice.suopu.controller;

import com.hemooffice.suopu.dto.*;
import com.hemooffice.suopu.exception.CusAuthException;
import com.hemooffice.suopu.exception.CusSystemException;
import com.hemooffice.suopu.service.ActivitiManageService;
import com.hemooffice.suopu.utils.SessionUtil;
import org.camunda.feel.syntaxtree.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        //获取当前登陆用户
        User lUser = (User)sessionUtil.getSessionObj("user");
        if(lUser == null){
            return Msg.send(401,"redis中用户信息为空,请重新登陆");
        }
        //封装数据
        OaActDef oaActDef = new OaActDef();
        oaActDef.setName(activitiDefParam.getName());
        oaActDef.setCategoryId(activitiDefParam.getCategory());
        oaActDef.setOrgId(organization.getOrgId());
        oaActDef.setCreateEmp(lUser.getUserId());
        oaActDef.setFormType(activitiDefParam.getFormType());
        oaActDef.setFormItem(activitiDefParam.getFormItem().toJSONString());
        oaActDef.setFlowChart(activitiDefParam.getFlowChart().toJSONString());
        activitiManageService.addActDef(oaActDef);
        return Msg.success("ok");
    }

    /**
     * 加载流程定义列表
     * @return
     */
    @GetMapping("/activitidef-list")
    public Msg findActDefList(){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        return Msg.success(activitiManageService.findActDefList(organization.getOrgId()));
    }

    /**
     * 加载流程表单列表
     * @return
     */
    @GetMapping("/activitidef-formitemlist")
    public Msg findActDefFormItem(){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        return Msg.success(activitiManageService.findActDefFormItem(organization.getOrgId()));
    }

    /**
     * 启用或者停用流程
     * @param oaActDef
     * @return
     */
    @PostMapping("/activitidef-updateactive")
    public Msg updateActDefActive(@RequestBody OaActDef oaActDef){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        try {
            return Msg.success(activitiManageService.updateActDefActive(organization.getOrgId(),oaActDef.getId(),oaActDef.getActive()));
        } catch (CusAuthException e) {
            e.printStackTrace();
            return Msg.send(505,e.getMessage());
        }
    }

    /**
     * 加载流程表单列表
     * @return
     */
    @GetMapping("/activitidef-formitemlistbyid")
    public Msg findActDefFormItemById(@NotNull(message = "审批类别id不能为空") @RequestParam("id") Integer id){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        return Msg.success(activitiManageService.findActDefFormItemById(organization.getOrgId(),id));
    }
    /**
     * 加载流程定义bpmn
     * @return
     */
    @GetMapping("/activitidef-bpmnbyid")
    public Msg findActDefBpmnById(@NotNull(message = "审批类别id不能为空") @RequestParam("id") Integer id){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        return Msg.success(activitiManageService.findActDefBpmn(organization.getOrgId(),id));
    }

    /**
     * 更新流程定义表单定义
     * @return
     */
    @PostMapping("/activitidef-updateactdefform")
    public Msg updaeActDefForm(@RequestBody OaActDef oaActDef){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        oaActDef.setOrgId(organization.getOrgId());
        try {
            return Msg.success(activitiManageService.updateActDefForm(oaActDef));
        } catch (CusAuthException e) {
            e.printStackTrace();
            return Msg.send(505,e.getMessage());
        }
    }
    /**
     * 更新流程定义表单定义
     * @return
     */
    @PostMapping("/activitidef-updateactdefbpmn")
    public Msg updaeActDefBpmn(@RequestBody OaActDef oaActDef){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        oaActDef.setOrgId(organization.getOrgId());
        try {
            return Msg.success(activitiManageService.updateActDefBpmn(oaActDef));
        } catch (CusAuthException e) {
            e.printStackTrace();
            return Msg.send(505,e.getMessage());
        }
    }

    /**
     * 加载流程定义信息
     * @return
     */
    @GetMapping("/activitidef-byid")
    public Msg findActDefById(@NotNull(message = "审批类别id不能为空") @RequestParam("id") Integer id){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        return Msg.success(activitiManageService.findActDefById(organization.getOrgId(),id));
    }

    /**
     *
     * @param file
     * @return
     */
    @PostMapping("/approval-uploadfile")
    public Msg insertOaActFile(@RequestParam(value = "bpmnId") Integer bpmnId,
                               @RequestParam(value = "elementId") String elementId,
                               @RequestParam(value = "file") MultipartFile file) {
        //获取当前登陆机构
        User user = (User)sessionUtil.getSessionObj("user");
        if(user == null){
            return Msg.send(401,"redis中用户信息为空,请重新登陆");
        }
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }

        try {
            return Msg.success(activitiManageService.insertOaActFile(organization.getOrgId(), bpmnId, elementId, file, user));
        } catch (CusAuthException e) {
            e.printStackTrace();
            return Msg.send(505, e.getMessage());
        } catch (CusSystemException e){
            e.printStackTrace();
            return Msg.send(505, e.getMessage());
        }
    }

    /**
     * 删除流程附件
     * @param fileId
     * @return
     */
    @GetMapping("/approval-removefile")
    public Msg removeOaActFile(@RequestParam("fileId") Integer fileId){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        return Msg.success(activitiManageService.removeOaActFile(organization.getOrgId(), fileId));
    }

    /**
     * 根据机构id、流程id、附件id查询附件列表
     * @param actId
     * @return
     */
    @GetMapping("/approval-filelist")
    public Msg findOaActFileListByFileIdOrgIdActId(@RequestParam("actId") Integer actId){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        return Msg.success(activitiManageService.findOaActFileListByFileIdOrgIdActId(organization.getOrgId(),actId));
    }
}
