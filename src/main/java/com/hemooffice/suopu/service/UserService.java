package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.User;
import com.hemooffice.suopu.exception.CusSystemException;
import com.hemooffice.suopu.exception.UserAuthException;

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
}
