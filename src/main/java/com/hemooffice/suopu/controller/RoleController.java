package com.hemooffice.suopu.controller;

import com.hemooffice.suopu.dto.Msg;
import com.hemooffice.suopu.dto.Organization;
import com.hemooffice.suopu.service.RoleService;
import com.hemooffice.suopu.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色
 */
@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private SessionUtil sessionUtil;
    /**
     * 加载当前机构所有角色
     * @return
     */
    @GetMapping("/rolelist")
    public Msg findRoleList(){
        //得到当前登录机构信息放入参数
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        return Msg.success(roleService.findRoleList(organization.getOrgId()));
    }
}
