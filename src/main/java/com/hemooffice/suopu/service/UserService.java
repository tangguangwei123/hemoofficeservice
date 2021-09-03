package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.User;
import com.hemooffice.suopu.exception.CusSystemException;
import com.hemooffice.suopu.exception.UserAuthException;

import java.util.List;

public interface UserService {
    /**
     * 根据用户名查询用户
     * @param userAccount
     * @return
     */
    public User findUserByUserAccount(String userAccount);

    /**
     * 登录
     * @param user
     * @return
     */
    public String login(User user) throws UserAuthException, CusSystemException;

    /**
     * 根据机构id获取机构所有用户
     * @param orgId
     * @return
     */
    public List<User> findUsersByOrgId(Integer orgId);

    /**
     * 新增用户
     * @return
     */
    public int addUser(User user) throws CusSystemException;
}
