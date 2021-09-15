package com.hemooffice.suopu.controller;

import com.hemooffice.suopu.dto.Msg;
import com.hemooffice.suopu.dto.Organization;
import com.hemooffice.suopu.dto.Permission;
import com.hemooffice.suopu.dto.Role;
import com.hemooffice.suopu.exception.CusAuthException;
import com.hemooffice.suopu.service.PermissionService;
import com.hemooffice.suopu.service.RoleService;
import com.hemooffice.suopu.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Autowired
    private PermissionService permissionService;
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

    /**
     * 新增机构角色
     * @return
     */
    @PostMapping("/role-add")
    public Msg addOrgRole(@Validated @RequestBody Role role){
        //得到当前登录机构信息放入参数
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        role.setOrgId(organization.getOrgId());
        return Msg.success(roleService.addOrgRole(role));
    }

    /**
     * 删除机构角色
     * @return
     */
    @GetMapping("/role-delete")
    public Msg deleteOrgRole(@RequestParam("roleId") Integer roleId){
        //得到当前登录机构信息放入参数
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        int result = 0;
        try {
            result = roleService.deleteRole(organization.getOrgId(),roleId);
        } catch (CusAuthException e) {
            e.printStackTrace();
            return Msg.send(505,e.getMessage());
        }
        return Msg.success(result);
    }
    /**
     *根据角色和机构加载权限
     * @return
     */
    @GetMapping("/role-permissionlist")
    public Msg findPermissionListByRoleId(@NotNull(message = "角色ID不能为空") @RequestParam("roleId") Integer roleId){
        //得到当前登录机构信息放入参数
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        List<Permission> permissionList = permissionService.findPermissionListByRoleId(organization.getOrgId(),roleId);

        return Msg.success(permissionList);
    }
}
