package com.hemooffice.suopu.controller;

import com.hemooffice.suopu.dto.Dept;
import com.hemooffice.suopu.dto.Msg;
import com.hemooffice.suopu.dto.Organization;
import com.hemooffice.suopu.service.DeptService;
import com.hemooffice.suopu.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
