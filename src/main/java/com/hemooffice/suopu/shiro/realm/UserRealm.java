package com.hemooffice.suopu.shiro.realm;

import com.hemooffice.suopu.dto.Role;
import com.hemooffice.suopu.dto.User;
import com.hemooffice.suopu.service.UserService;
import com.hemooffice.suopu.service.RoleService;
import com.hemooffice.suopu.shiro.JWTUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.hemooffice.suopu.service.ResourceService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个基于JDBC的Realm,继承AuthorizingRealm可以看见需要重写两个方法,doGetAuthorizationInfo和doGetAuthenticationInfo
 *
 * @author xqlee
 */
@Component
public class UserRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;
    @Resource(name = "redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * 该Realm仅支持自定义的StatelessAuthenticationToken类型Token，其他类型处理将会抛出异常
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessAuthenticationToken;
    }

    /***
     * 获取用户授权
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)   {
        logger.info("##################执行Shiro权限认证##################");
        String userName = JWTUtils.getUsername(principals.toString());

        User user = null;
        try {
            user = userService.findUserByUsername(userName.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("执行Shiro权限认证:"+e.toString());
            throw new AuthorizationException("权限验证加载用户异常！");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        if (user != null) {
            if(user.getStatus() == 0){
                logger.error("执行Shiro权限认证:账户"+user.getUserName()+"已停用");
                throw new AuthorizationException("账户已停用！");
            }

            //赋予角色
            List<Role> roles = roleService.findRoleListByUserId(user.getUserId());
            for (Role role : roles) {
                info.addRole(role.getRoleDesc());
                //赋予资源
                List<Permission> permissions = permissionService.findPermissionListByRoleId(role.getRoleId());
                if(null != permissions && permissions.size() > 0){
                   for (Permission permission : permissions) {
                       info.addStringPermission(permission.getFunctionDesc());
                       logger.info(permission.getFunctionCaption());
                   }
               }
            }
        }else {
            logger.error("权限验证异常:用户为null");
            throw new AuthorizationException("权限验证异常:用户为null");
        }
        return info;
    }

    /**
     * 获取用户认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        logger.info("##################执行Shiro登陆认证##################");
        String token = (String) authenticationToken.getCredentials();
        //从token中获取用户名
        String username = JWTUtils.getUsername(token);
        //获取数据库中存取的用户，密码是加密后的
        User user = null;
        try {
            user = userMapper.findUserByUsername(username.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknownAccountException("用户身份认证异常！");
        }
        if (user != null) {
            if(user.getActive() == 0){
                logger.error("执行Shiro登陆认证:账户"+user.getUserName()+"已停用");
                throw new AuthorizationException("账户已停用！");
            }

            // 密码验证
            if (!JWTUtils.verify(token, username, user.getUserPass())) {
                throw new IncorrectCredentialsException("token验证异常:无效token");
            }
            //重新设置redes中信息过期时间
            //获取用户、机构信心
            User redisUser = null;
            Organization redisOrganization = null;
            String requestServletPath = "";
            try{
                requestServletPath = redisTemplate.opsForValue().get("requestServletPath").toString();
               if(!GlobalParam.IGNOREREFRESHSERVLETPATH.contains(requestServletPath)){
                   user = (User) redisTemplate.opsForValue().get(token);
                   redisOrganization = (Organization)redisTemplate.opsForValue().get(user.getUserId() + "");
                   redisTemplate.opsForValue().set(token,user, GlobalParam.CACHETIME, TimeUnit.SECONDS);
                   redisTemplate.opsForValue().set(user.getUserId()+ "",redisOrganization,GlobalParam.CACHETIME,TimeUnit.SECONDS);
               }
            }catch (Exception e){
                throw new UnknownAccountException("用户身份认证异常！");
            }
            return new SimpleAuthenticationInfo(token, token, token+user.getUserId());
        } else {
            throw new UnknownAccountException("token验证异常:未知账户");
        }
    }
}