package com.hemooffice.suopu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.hemooffice.suopu.constant.GlobalParam;
import com.hemooffice.suopu.dto.Msg;
import com.hemooffice.suopu.dto.User;
import com.hemooffice.suopu.exception.CusSystemException;
import com.hemooffice.suopu.exception.UserAuthException;
import com.hemooffice.suopu.mapper.UserMapper;
import com.hemooffice.suopu.service.UserService;
import com.hemooffice.suopu.shiro.JWTUtils;
import com.hemooffice.suopu.utils.CommonUtils;
import com.hemooffice.suopu.utils.FileUtil;
import com.hemooffice.suopu.utils.IOUtils;
import com.hemooffice.suopu.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 通过用户名查找用户
     * @param userAccount
     * @return
     */
    @Override
    public User findUserByUserAccount(String userAccount) {
        return userMapper.findUserByUserAccount(userAccount);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public String login(User user) throws UserAuthException, CusSystemException {

        if(user == null){
            throw new UserAuthException("登录用户信息为空！");
        }
        if(StringUtils.isEmpty(user.getUserAccount())){
            throw new UserAuthException("用户名为空！");
        }
        if(StringUtils.isEmpty(user.getPassword())){
            throw new UserAuthException("密码为空！");
        }
        //从数据库加载用户信息
        User dUser = userMapper.findUserByUserAccount(user.getUserAccount());
        if(dUser == null){
            throw new UserAuthException("没有此用户！");
        }
        //解密密码
        String priKeyBae64 = null;
        PrivateKey privateKey =null;
        String dePassword = null;
        try {
            priKeyBae64 = IOUtils.readFile(new File(FileUtil.getResourceBasePath()+ GlobalParam.PRIKEY));

            privateKey = RSAUtil.string2PrivateKey(priKeyBae64);
            //解密密码
            dePassword = RSAUtil.privateDecrypt(user.getPassword(),privateKey);

        }catch (Exception e) {
            e.printStackTrace();
            throw new CusSystemException("用户登录解密密码异常！");
        }

        try {
            if(!CommonUtils.userPassMd5(dePassword).equals(dUser.getPassword())){
                throw new UserAuthException("密码错误！");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new CusSystemException("用户密码MD5加密异常！");
        }
        //验证成功，返回token
        String token = JWTUtils.sign(dUser.getUserAccount(), dUser.getPassword());
        if(token == null){
            throw new CusSystemException("用户登录生成token异常！");
        }
        return token;
    }

    /**
     * 根据机构id获取机构所有用户
     * @param orgId
     * @return
     */
    @Override
    public List<User> findUsersByOrgId(Integer orgId) {
        return userMapper.findUsersByOrgId(orgId);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Override
    public int addUser(User user) throws CusSystemException {
        //设置初始密码为 123456
        try {
            user.setPassword(CommonUtils.userPassMd5("123456"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new CusSystemException("设置初始密码异常！");
        }
        return userMapper.addUser(user);
    }

    /**
     * 根据角色Id加载用户列表
     * @param orgId
     * @param roleId
     * @return
     */
    @Override
    public List<User> findUserListByRoleId(Integer orgId, Integer roleId) {
        return userMapper.findUserListByRoleId(orgId,roleId);
    }
}
