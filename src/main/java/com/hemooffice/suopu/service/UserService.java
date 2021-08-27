package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.User;

public interface UserService {
    /**
     * 根据用户名查询用户
     * @param userNmae
     * @return
     */
    public User findUserByUsername(String userNmae);
}
