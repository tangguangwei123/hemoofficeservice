package com.hemooffice.suopu.shiro.config;

import com.hemooffice.suopu.shiro.realm.UserRealm;
import com.hemooffice.suopu.constant.GlobalParam;
import com.hemooffice.suopu.shiro.cache.RedisCacheManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/***
 *
 * @author xqlee
 *
 */
@Configuration
public class ShiroConfig {

    private static final String JWT_FILTER_NAME = "jwt";
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * redis缓存方案
     *
     * @return
     */
    @Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager() {
        //过期时间
        long cacheLive = GlobalParam.CACHETIME;
        //前缀
        String cachePrefix = "shiro-redis-cache";

        RedisCacheManager redisCacheManager = new RedisCacheManager();
        //cache过期时间及前缀
        redisCacheManager.setCacheLive(cacheLive);
        redisCacheManager.setCacheKeyPrefix(cachePrefix);
        redisCacheManager.setRedisTemplate(redisTemplate);
        return redisCacheManager;
    }

    /****
     * 自定义Real
     *
     * @return
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm realm = new UserRealm();
        // 根据情况使用缓存器
        realm.setCacheManager(redisCacheManager());// shiroEhCacheManager()
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        realm.setAuthorizationCachingEnabled(true);

        return realm;
    }

    /**
     * 自定义的无状态（无session）Subject工厂
     *
     * @return
     */
    @Bean
    public StatelessDefaultSubjectFactory subjectFactory() {

        return new StatelessDefaultSubjectFactory();
    }

    /**
     * sessionManager通过sessionValidationSchedulerEnabled禁用掉会话调度器，
     * 因为我们禁用掉了会话，所以没必要再定期过期会话了。
     *
     * @return
     */
    @Bean
    public DefaultSessionManager sessionManager() {
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        //缓存
        sessionManager.setCacheManager(redisCacheManager());
        return sessionManager;
    }

    /***
     * 安全管理配置
     *
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 注意这里必须配置securityManager
        SecurityUtils.setSecurityManager(securityManager);
        // 根据情况选择缓存器
        securityManager.setCacheManager(redisCacheManager());// shiroEhCacheManager()

        // 设置Subject工厂
        securityManager.setSubjectFactory(subjectFactory());

        // 配置
        securityManager.setRealm(userRealm());

        // session
        securityManager.setSessionManager(sessionManager());
        // 禁用Session作为存储策略的实现。
        DefaultSubjectDAO defaultSubjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluatord = (DefaultSessionStorageEvaluator) defaultSubjectDAO
                .getSessionStorageEvaluator();
        defaultSessionStorageEvaluatord.setSessionStorageEnabled(false);

        return securityManager;
    }

    /**
     * 配置shiro的拦截器链工厂,默认会拦截所有请求
     *
     * @return
     */
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put(JWT_FILTER_NAME, new StatelessAccessControlFilter(redisTemplate));
        factoryBean.setFilters(filterMap);
        factoryBean.setUnauthorizedUrl("/api/401");
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();

        filterChainDefinitionManager.put("/api/login", "anon");
        filterChainDefinitionManager.put("/api/401/**", "anon");
        filterChainDefinitionManager.put("/api/act/approval-filedownload", "anon");
        //filterChainDefinitionManager.put("/api/**", "anon");
        filterChainDefinitionManager.put("/api/publickeystr", "anon");
        //filterChainDefinitionManager.put("/api/generateRsa", "anon");
        filterChainDefinitionManager.put("/druid/**", "anon");
        //filterChainDefinitionManager.put("/user/**", "authc");
        filterChainDefinitionManager.put("/**", JWT_FILTER_NAME);

        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
        return factoryBean;
    }

    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}