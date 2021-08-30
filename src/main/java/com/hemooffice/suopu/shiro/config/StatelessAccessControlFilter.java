package com.hemooffice.suopu.shiro.config;

import com.hemooffice.suopu.dto.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * 该过滤器需在shiro配置类中加入filter链
 */
public class StatelessAccessControlFilter extends BasicHttpAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(StatelessAccessControlFilter.class);

    private RedisTemplate<String,Object> redisTemplate;

    /**
     * header中token标志
     */
    private static String LOGIN_SIGN = "token";
    private static final String CHARSET = "UTF-8";

    public StatelessAccessControlFilter(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    /**
     * 过滤器中的token全局异常处理无法捕捉，所以直接响应
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) {
            try {
                executeLogin(request, response);
            }catch (IncorrectCredentialsException e) {
                response401(request,response,401,e.getMessage());
                return false;
            }catch (UnknownAccountException e) {
                response401(request,response,401,e.getMessage());
                return false;
            } catch (IllegalStateException e) {
                response401(request,response,401,e.getMessage());
                return false;
            }catch (Exception e) {
                e.printStackTrace();
                response401(request,response,500,e.getMessage());
                return false;
            }
        return true;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        //判断是否是登录请求
        String authorization = req.getHeader(LOGIN_SIGN);
        return authorization != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String token = req.getHeader(LOGIN_SIGN);
        // 如果是OPTIONS则结束请求
        if (HttpMethod.OPTIONS.toString().equals(req.getMethod())) {
            res.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        if (null == token) {
            String msg = "token为空！";
            throw new IllegalStateException(msg);
        }
        //从redis中查询用户信息
        User user = (User)redisTemplate.opsForValue().get(token);
        //将请求路径放入redis
        redisTemplate.opsForValue().set("requestServletPath",((HttpServletRequest) request).getServletPath(),10, TimeUnit.SECONDS);
        //如果没有用户信息，重新登陆
        if(null == user){
            String msg = "登录已失效，请重新登陆！";
            throw new IllegalStateException(msg);
        }
        StatelessAuthenticationToken jwtToken  = new StatelessAuthenticationToken(token);
        SecurityUtils.getSubject().login(jwtToken);
        return true;
    }

    /**
     * 将非法请求跳转到 /401
     */
    private void response401(ServletRequest req, ServletResponse resp,int status,String errMessage) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            HttpServletRequest httpServletRequest = (HttpServletRequest) req;
            httpServletResponse.sendRedirect("/api/401?status="+status+"&message="+getURLEncoderString(errMessage));
            logger.info(errMessage);
            //httpServletRequest.getRequestDispatcher("/api/401?status="+status+"&message="+getURLEncoderString(errMessage));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    /**
     *对中文URL进行编码，由于编码过滤器应该在shiro过滤器先执行了，所有shiro中请求用中文就不经过编码过滤器 所以需要手动编码
     * @param str
     * @return
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
