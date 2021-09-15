package com.hemooffice.suopu.controller;

import com.hemooffice.suopu.dto.Msg;
import com.hemooffice.suopu.dto.Organization;
import com.hemooffice.suopu.dto.Permission;
import com.hemooffice.suopu.service.PermissionService;
import com.hemooffice.suopu.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 资源权限
 */
@RestController
@RequestMapping("/api")
public class PermissionController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    private SessionUtil sessionUtil;
    @Autowired
    private PermissionService permissionService;
    @GetMapping("/permission-list")
    public Msg findPermissionListByOrgId(){
        //得到当前登录机构信息放入参数
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        List<Permission> permissionList = permissionService.findPermissionListByOrgId(organization.getOrgId());

        return Msg.success(permissionList);
    };
}
