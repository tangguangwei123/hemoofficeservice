package com.hemooffice.suopu.exception;

/**
 * 自定义用户验证异常
 */
public class UserAuthException extends Exception{
    public UserAuthException(){}

    public UserAuthException(String message){ super(message); }
}
