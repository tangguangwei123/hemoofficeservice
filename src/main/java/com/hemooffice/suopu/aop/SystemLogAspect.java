package com.hemooffice.suopu.aop;

import com.alibaba.fastjson.JSONObject;
import com.hemooffice.suopu.annotation.SystemControllerLog;
import com.hemooffice.suopu.annotation.SystemServiceLog;
import com.hemooffice.suopu.dto.User;
import com.hemooffice.suopu.dto.UserLog;
import com.hemooffice.suopu.service.SysLogService;
import com.hemooffice.suopu.utils.IpAdrressUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Title: SystemControllerLog
 * @date 2018年8月31日
 * @version V1.0
 * Description: 切点类
 */
@Aspect
@Component
@SuppressWarnings("all")
public class SystemLogAspect {
    //注入Service用于把日志保存数据库，实际项目入库采用队列做异步
    @Resource
    private SysLogService actionService;
    @Resource(name = "redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;
    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    //Service层切点
    @Pointcut("@annotation(com.hemooffice.suopu.annotation.SystemServiceLog)")
    public void serviceAspect(){
    }

    //Controller层切点
    @Pointcut("@annotation(com.hemooffice.suopu.annotation.SystemControllerLog)")
    public void controllerAspect(){
    }

    /**
     * @Description  前置通知  用于拦截Controller层记录用户的操作
     * @date 2018年9月3日 10:38
     */

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //读取session中的用户
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        //从redis中查询用户信息
        User user = (User)redisTemplate.opsForValue().get(token);
        String ip =IpAdrressUtil.getIpAdrress(request);

        try {
            //*========控制台输出=========*//
            System.out.println("==============前置通知开始==============");

            //*========数据库日志=========*//
            UserLog action = new UserLog();
            action.setOperaDesc(getControllerMethodDescription(joinPoint));
            action.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            action.setIpaddr(ip);
            action.setOperaState(1);
            action.setOperaTime(new Date());
            if (null != user){
                action.setUserId(user.getUserId());
                action.setEmpName(user.getUserName());
                //保存数据库
                actionService.save(action);
            }

        }catch (Exception e){
            e.printStackTrace();
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息：{}",e.getMessage());
        }
    }

    /**
     * @Description  异常通知 用于拦截service层记录异常日志
     * @date 2018年9月3日 下午5:43
     */
    @AfterThrowing(pointcut = "serviceAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable e){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //读取session中的用户
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        //从redis中查询用户信息
        User user = (User)redisTemplate.opsForValue().get(token);
        //获取请求ip
        String ip = IpAdrressUtil.getIpAdrress(request);
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs()!=null&&joinPoint.getArgs().length>0){
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params+= JSONObject.toJSON(joinPoint.getArgs()[i])+";";
            }
        }
        try{
            /*========控制台输出=========*/
            System.out.println("=====异常通知开始=====");
            System.out.println("异常代码:" + e.getClass().getName());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));
            System.out.println("请求人:" + user.getUserName());
            System.out.println("请求IP:" + ip);
            System.out.println("请求参数:" + params);
            /*==========数据库日志=========*/
            UserLog action = new UserLog();
            action.setOperaDesc(getServiceMethodDescription(joinPoint));
            action.setUserId(user.getUserId());
            action.setIpaddr(ip);
            action.setOperaTime(new Date());
            //保存到数据库
            actionService.save(action);
        }catch (Exception ex){
            ex.printStackTrace();
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
    }


    /**
     * @Description  获取注解中对方法的描述信息 用于service层注解
     * @date 2018年9月3日 下午5:05
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint)throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }



    /**
     * @Description  获取注解中对方法的描述信息 用于Controller层注解
     * @date 2018年9月3日 上午12:01
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
}