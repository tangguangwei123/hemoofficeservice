package com.hemooffice.suopu.controller;

import com.hemooffice.suopu.dto.Dept;
import com.hemooffice.suopu.dto.DeptParam;
import com.hemooffice.suopu.dto.Msg;
import com.hemooffice.suopu.dto.Organization;
import com.hemooffice.suopu.service.DeptService;
import com.hemooffice.suopu.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeptController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
    //session获取类
    @Autowired
    private SessionUtil sessionUtil;
    @Autowired
    private DeptService deptService;
    /**
     * 获取登录机构部门列表
     * @return
     */
    @GetMapping("/currentorg-deptlist")
    public Msg findDeptListByLoginOrgId(){
        //获取当前的登录部门
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        //获当前机构所有部门
        List<Dept> deptList = deptService.findDeptByOrgId(organization.getOrgId());

        return Msg.success(deptList);
    }

    /**
     * 新增部门
     * @param deptParam
     * @return
     */
    @PostMapping("/adddept")
    public Msg addDept(@Validated @RequestBody DeptParam deptParam){
        //获取当前的登录部门
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        deptParam.setOrgId(organization.getOrgId());

        return Msg.success(deptService.setDeptUser(deptParam));
    }

    /**
     * 删除部门
     * @param deptId
     * @return
     */
    @GetMapping("/dept-delete")
    public Msg deleteDept(@RequestParam("deptId") Integer deptId){
        return Msg.success(deptService.deleteDept(deptId));
    }

    /**
     * 加载当前机构所有部门和部门下用户
      * @return
     */
    @GetMapping("/dept-user-list")
    public Msg findDeptListAndUserList(){
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        return Msg.success(deptService.findDeptListAndUserList(organization.getOrgId()));
    }

    /**
     * 查询指定部门下面用户
     * @return
     */
    @GetMapping("/dept-users")
    public Msg findUsersByDeptId(@RequestParam("deptId") Integer deptId){
        if(deptId == null){
           return Msg.send(505,"请选择部门！");
        }
        return Msg.success(deptService.findUsersByDeptId(deptId));
    }
}
