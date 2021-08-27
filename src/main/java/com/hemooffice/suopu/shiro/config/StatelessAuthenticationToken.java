package com.hemooffice.suopu.shiro.config;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * 自定义实现Shiro的一个认证Token
 *
 * @author xq
 */
public class StatelessAuthenticationToken implements AuthenticationToken {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String token;// 用户登录后获取的令牌；
    // 构造函数

    public StatelessAuthenticationToken() {
    }

    public StatelessAuthenticationToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}