package com.hemooffice.suopu.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class CommonUtils {

    /**
     * 用户密码MD5运算存储
     * 用户密码保存规则：用 Hemosoft77加用户密码然后md5 32位大写保存，这样可以吧？验证时也这样处理，这样可以防止简单密码攻击
     * @param password
     * @return
     */
    public static String userPassMd5(String password) throws UnsupportedEncodingException {
        return DigestUtils.md5DigestAsHex(("suopusoft_"+password).getBytes("utf-8")).toUpperCase();
    }
    /**
     * 生成uuid
     * @return
     */
    public static String GetGUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
