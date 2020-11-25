package com.yxj.jiaxiaohulian.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.yxj.jiaxiaohulian.realms.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//@Configuration
public class ShiroConfig1 {


//    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * *
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * *
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * * @return
     */
//    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

//    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("defaultWebSecurityManager") DefaultSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

//    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }




    //ShiroFilterFactoryBean  第三步
//    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){

        //添加shiro的内置过滤器
        /*
            anon:无需认证就可以访问
            authc:必须认证才可以访问
            user:必须拥有 记住我 功能才能用
            perms:拥有对某个资源的权限才能访问
            role:拥有某个角色权限才能访问
         */
        //拦截
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //给filter 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统的受限资源
        //配置系统的公共资源
        Map<String,String> map = new LinkedHashMap<>();
        map.put("/bg/**/*","anon"); //anon 设置为公共资源 （需设置在authc的上边）
        map.put("/img/**/*","anon");
        map.put("/css/**/*","anon");
        map.put("/error/**/*","anon");
        map.put("/font/**/*","anon");
        map.put("/webjars/**/*","anon");
        map.put("/js/**/*","anon");

        map.put("/userLogin.html","anon");
        map.put("/login.do","anon");
        map.put("/userRegister.html","anon");
        map.put("/userRegister.do","anon");
        map.put("/userXieYi.html","anon");
        map.put("/findYuanXi.do","anon");
        map.put("/findAllUtName.do","anon");
        map.put("/checkUNameExsit.do","anon");
        map.put("/adminLogin.html","anon");
        map.put("/admin/adminLogin.do","anon");
//        map.put("/admin/**/*","anon");



        map.put("/**","authc");
        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/userLogin.html");

        shiroFilterFactoryBean.setUnauthorizedUrl("/noLogin.html");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurityManager  第二步
//    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        //给安全管理器设置realm
//        defaultWebSecurityManager.setRealm(userRealm);

        List list = new ArrayList();
        list.add(userRealm());
//        list.add(adminRealm());
        defaultWebSecurityManager.setRealms(list);

        return defaultWebSecurityManager;
    }

    //创建 realm 对象   需要自定义类  第一步
//    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();

        //修改密码凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        userRealm.setCredentialsMatcher(credentialsMatcher);


        //开启缓存管理(ehcache)
        userRealm.setCacheManager(new EhCacheManager());
        userRealm.setCachingEnabled(true); //开启全局缓存
        userRealm.setAuthenticationCachingEnabled(true); //认证缓存
        userRealm.setAuthenticationCacheName("authenticationCache"); //给缓存取名（不取就用默认的名字）
        userRealm.setAuthorizationCachingEnabled(true); //开启授权缓存
        userRealm.setAuthorizationCacheName("authorizationCache");

        //开启缓存管理(redis)
        /*customerRealm.setCacheManager(new RedisCacheManager());
        customerRealm.setCachingEnabled(true); //开启全局缓存
        customerRealm.setAuthenticationCachingEnabled(true); //认证缓存
        customerRealm.setAuthenticationCacheName("authenticationCache"); //给缓存取名（不取就用默认的名字）
        customerRealm.setAuthorizationCachingEnabled(true); //开启授权缓存
        customerRealm.setAuthorizationCacheName("authorizationCache");*/


        return userRealm;
    }

  /*  @Bean
    public AdminRealm adminRealm(){
        AdminRealm adminRealm = new AdminRealm();

        //修改密码凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        adminRealm.setCredentialsMatcher(credentialsMatcher);*/


        //开启缓存管理(ehcache)
        /*adminRealm.setCacheManager(new EhCacheManager());
        adminRealm.setCachingEnabled(true); //开启全局缓存
        adminRealm.setAuthenticationCachingEnabled(true); //认证缓存
        adminRealm.setAuthenticationCacheName("authenticationCache"); //给缓存取名（不取就用默认的名字）
        adminRealm.setAuthorizationCachingEnabled(true); //开启授权缓存
        adminRealm.setAuthorizationCacheName("authorizationCache");*/

        //开启缓存管理(redis)
        /*customerRealm.setCacheManager(new RedisCacheManager());
        customerRealm.setCachingEnabled(true); //开启全局缓存
        customerRealm.setAuthenticationCachingEnabled(true); //认证缓存
        customerRealm.setAuthenticationCacheName("authenticationCache"); //给缓存取名（不取就用默认的名字）
        customerRealm.setAuthorizationCachingEnabled(true); //开启授权缓存
        customerRealm.setAuthorizationCacheName("authorizationCache");*/


//        return adminRealm;
//    }

    //整合ShiroDialect：用来整合shiro和thymeleaf
//    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }




}
