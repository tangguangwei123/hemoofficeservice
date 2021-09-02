package com.hemooffice.suopu.utils;

import com.hemooffice.suopu.dto.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * session存储管理工具
 */
@Component
public class SessionUtil {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;
    /**
     *根据后缀获取redis中对象
     * @param suffix
     * @return
     */
    public Object getSessionObj(String suffix){
        Object object = null;
        try{
            String token = SecurityUtils.getSubject().getPrincipal().toString();
            object = redisTemplate.opsForValue().get(token+":"+suffix);
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
        return object;
    }
}
