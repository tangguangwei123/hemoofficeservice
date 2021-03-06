package com.hemooffice.suopu.controller;

import com.hemooffice.suopu.constant.GlobalParam;
import com.hemooffice.suopu.dto.Msg;
import com.hemooffice.suopu.dto.Organization;
import com.hemooffice.suopu.dto.Permission;
import com.hemooffice.suopu.dto.User;
import com.hemooffice.suopu.exception.CusAuthException;
import com.hemooffice.suopu.exception.CusSystemException;
import com.hemooffice.suopu.exception.UserAuthException;
import com.hemooffice.suopu.service.OrganizationService;
import com.hemooffice.suopu.service.PermissionService;
import com.hemooffice.suopu.service.UserService;
import com.hemooffice.suopu.utils.FileUtil;
import com.hemooffice.suopu.utils.IOUtils;
import com.hemooffice.suopu.utils.RSAUtil;
import com.hemooffice.suopu.utils.SessionUtil;
import org.apache.shiro.SecurityUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.ext.MS874;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@Validated
@RequestMapping("/api")
public class UserController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private SessionUtil sessionUtil;
    @Autowired
    private PermissionService permissionService;
    //redis
    @Resource(name = "redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 调试测试
     * @return
     */
    @GetMapping("/test")
    public Msg test(){
        logger.info("/api/test 请求成功！");
        return Msg.send(100,"测试成功！");
    }
    /**
     * 获取加密公钥
     * @return
     */
    @GetMapping(value = "/publickeystr")
    public Msg getPublicKey() {
        // 从 公钥保存的文件 读取 公钥的Base64文本
        String pubKeyBase64 = null;
        PublicKey publicKey = null;
        try {
            pubKeyBase64 = IOUtils.readFile(new File(FileUtil.getResourceBasePath()+ GlobalParam.PUBKEY));
            //获得公钥对象
            //publicKey  = RSAUtil.string2PublicKey(pubKeyBase64);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("获取加密公钥:"+e.getMessage());
            return Msg.send(500,e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("获取加密公钥:"+e.getMessage());
            return Msg.send(500,e.getMessage());
        }
        //返回公钥给客户端
        return Msg.success(pubKeyBase64);
    }
    /**
     * 重新生成并保存公公私钥对并保存
     * @return
     */
    @GetMapping(value = "/generateRsa")
    public Msg generateRsa(){
        //获取公钥并进行base64编码
        KeyPair keyPair = null;
        try {
            keyPair = RSAUtil.getKeyPair();
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("重新生成并保存公公私钥对并保存:"+e.getMessage());
            return Msg.send(1017,"获取公钥失败");
        }
        //获取公钥和私钥对象
        String publicKeyStr = RSAUtil.getPublicKey(keyPair);
        String privateKeyStr = RSAUtil.getPrivateKey(keyPair);

        try{
            // 保存 公钥和私钥 到指定文件
            IOUtils.writeFile(publicKeyStr, new File(FileUtil.getResourceBasePath()+GlobalParam.PUBKEY));
            IOUtils.writeFile(privateKeyStr, new File(FileUtil.getResourceBasePath()+GlobalParam.PRIKEY));
        }catch (IOException e){
            e.printStackTrace();
            return Msg.send(500,e.getMessage());
        }
        return Msg.send(200,"ok");
    }
    /**
     * shiro 部分异常响应处理
     * @param status
     * @param message
     * @return
     */
    @GetMapping(value = "/401",produces = "application/json;charset=utf-8")
    public Msg unauthorized(int status,String message) {
        logger.error("status:"+status + "message?:"+message);
        return Msg.send(status,message);
    }
    /**
     * 用户登录
     * @param user
     * @param request
     * @return
     */
    @PostMapping(value = "/login")
    public Msg login(@RequestBody User user, HttpServletRequest request){
        logger.info("登录传入参数："+user.toString());
        //获取token
        String token = null;
        User dUser = null;
        Organization organization = null;
        List<Permission> menuList = new ArrayList<>();
        try {
             token = userService.login(user);
             //根据userAccount获取数据库用户
             dUser = userService.findUserByUserAccount(user.getUserAccount());
             if(dUser == null){
                 return Msg.send(403,"用户信息为空！");
             }
             //根据登录当前登录用户加载机构信息
            organization = organizationService.findOrganizationByUserId(dUser.getUserId());
            //获取菜单信息返回前端
            menuList = new ArrayList<>();

            try {
                menuList =  permissionService.findUserMenuList(organization.getOrgId(),dUser.getUserId());
            } catch (CusAuthException e) {
                e.printStackTrace();
                return Msg.send(505,e.getMessage());
            }
            //存入token
            redisTemplate.opsForValue().set(token+ ":token",token,GlobalParam.CACHETIME,TimeUnit.SECONDS);
            //使用token做主键 将user信息存入redis
            redisTemplate.opsForValue().set(token+ ":user",dUser,GlobalParam.CACHETIME,TimeUnit.SECONDS);
            //存入机构信息到redis
            redisTemplate.opsForValue().set(token+ ":organization",organization,GlobalParam.CACHETIME,TimeUnit.SECONDS);
        } catch (UserAuthException e) {
            e.printStackTrace();
            return Msg.send(403,e.getMessage());
        }catch (CusSystemException e){
            e.printStackTrace();
            return Msg.fail(500,e.getMessage(),null);
        }
        //返回信息集合
        Map<String,Object> resMap = new HashMap<String,Object>();
        resMap.put("token",token);
        resMap.put("user",dUser);
        resMap.put("organization",organization);
        resMap.put("menuList",menuList);
        return Msg.success(resMap);
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    public Msg logout(){
        //获取用户、机构信心
        User user = null;
        try{
            //清空redis
            Set<String> keys = redisTemplate.keys("*");
            redisTemplate.delete(keys);
            //shiro登出
            SecurityUtils.getSubject().logout();

        }catch (NullPointerException e){
            e.printStackTrace();
            logger.error("登出系统:"+e.getMessage());
            return Msg.send(401,"登录已失效!");
        }
        return Msg.send(401,"注销登录");
    }

    /**
     * 获取当前登陆机构所有用户
     * @return
     */
    @GetMapping("/currentorg-userlist")
    public Msg getCrrentOrgUserList(){
        //获取当前登陆机构
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        //查询所有用户
        List<User> userList = userService.findUsersByOrgId(organization.getOrgId());

        return Msg.success(userList);
    }

    /**
     * 新增用户
     * @return
     */
    @PostMapping("/adduser")
    public Msg addUser(@Validated @RequestBody User user){
        //得到当前登录机构信息放入参数
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }

        user.setOrgId(organization.getOrgId());
        //添加
        try {
            userService.addUser(user);
        } catch (CusSystemException e) {
            e.printStackTrace();
            return Msg.fail(500,e.getMessage(),null);
        }

        return Msg.success(user.getUserId());
    }

    /**
     * 根据用户名查找用户
     * @return
     */
    @GetMapping("/getuser-useraccount")
    public Msg findUserByUerAccount(@NotBlank(message = "用户账户不能为空!") String userAccount){
        return Msg.success(userService.findUserByUserAccount(userAccount));
    }

    /**
     * 根据角色Id加载用户列表
     * @param roleId
     * @return
     */
    @GetMapping("/userlist-role")
    public Msg findUserListByRoleId(@NotNull(message = "角色ID不能为空")  @RequestParam("roleId") Integer roleId){
        //得到当前登录机构信息放入参数
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }
        return Msg.success(userService.findUserListByRoleId(organization.getOrgId(),roleId));
    }

    /**
     * 加载当前登录用户菜单
     * @return
     */
    @GetMapping("/user-menulist")
    public Msg findUserMenuList(){
        //得到当前登录机构信息放入参数
        User user = (User)sessionUtil.getSessionObj("user");
        if(user == null){
            return Msg.send(401,"redis中登录用户信息为空,请重新登陆");
        }
        //得到当前登录机构信息放入参数
        Organization organization = (Organization)sessionUtil.getSessionObj("organization");
        if(organization == null){
            return Msg.send(401,"redis中机构信息为空,请重新登陆");
        }

        List<Permission> menuList = new ArrayList<>();

        try {
            menuList =  permissionService.findUserMenuList(organization.getOrgId(),user.getUserId());
        } catch (CusAuthException e) {
            e.printStackTrace();
            return Msg.send(505,e.getMessage());
        }
        return Msg.success(menuList);
    }
}
