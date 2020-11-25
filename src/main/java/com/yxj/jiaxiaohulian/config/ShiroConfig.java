package com.yxj.jiaxiaohulian.config;

import com.yxj.jiaxiaohulian.realms.UserRealm;
import com.yxj.jiaxiaohulian.util.cache.RedisCacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootConfiguration
public class ShiroConfig {

    private Logger logger= LoggerFactory.getLogger(ShiroConfig.class);

    //拦截的过滤器的配置
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        logger.info("shiro的过滤器执行了.....");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        Map<String,String> map=new LinkedHashMap<>();
        //第一个参数是路劲  第二个参数是过滤器的名字
        /**
         * 常见的过滤器的名字以及含义
         * /**：当前以及目录和后面的所有子目录全部都匹配上
         *       127.0.0.1:8080/bobo     127.0.0.1:8080/bobo/xiaobobo
         * /* ：这个相当于只是匹配当前这一级的节点   127.0.0.1:8080/bobo
         *      127.0.0.1:8080/bobo/xiaobobo
         * authc：认证的过滤器
         * anon： 表示的是/toIndex这个请求 不认证就可以访问 （匿名访问）
         *        maps.put("/toIndex","anon");
         * logout：登陆过滤器
         *        maps.put("/logout","logout")
         * perms：权限控制的
         * roles:具有某一个角色才能访问
         *
         * 注意事项：  /** 这个配置一定是最后 一个
         *
         */
        //maps.put("/toIndex","anon");  //表示的是不需要认证就可以访问
        //需身份认证
        map.put("/bg/**","anon"); //anon 设置为公共资源 （需设置在authc的上边）
        map.put("/img/**","anon");
        map.put("/css/**","anon");
        map.put("/error/**","anon");
        map.put("/font/**","anon");
        map.put("/webjars/**","anon");
        map.put("/js/**","anon");

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
//        map.put("add.html","role[]");
//        map.put("/admin/**/*","anon");

        map.put("/**","authc");
        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/userLogin.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noLogin.html");
        shiroFilterFactoryBean.setLoginUrl("/userLogin.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }


    //配置下SecurityManager
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置校验器
//        securityManager.setAuthenticator(authenticator());
        /*List<Realm> realms=new ArrayList<>();
        realms.add(adminRealm());
        realms.add(userRealm());*/

        //设置Realm
        securityManager.setRealm(userRealm());
        return securityManager;
    }



    //配置的是userRealm
    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        logger.info("userRealm的过滤器执行了.....");
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        //开启缓存管理(ehcache)
        /*userRealm.setCacheManager(new EhCacheManager());
        userRealm.setCachingEnabled(true); //开启全局缓存
        userRealm.setAuthenticationCachingEnabled(true); //认证缓存
        userRealm.setAuthenticationCacheName("authenticationCache"); //给缓存取名（不取就用默认的名字）
        userRealm.setAuthorizationCachingEnabled(true); //开启授权缓存
        userRealm.setAuthorizationCacheName("authorizationCache");*/

        //开启缓存管理(redis)
        userRealm.setCacheManager(new RedisCacheManager());
        userRealm.setCachingEnabled(true); //开启全局缓存
        userRealm.setAuthenticationCachingEnabled(true); //认证缓存
        userRealm.setAuthenticationCacheName("authenticationCache"); //给缓存取名（不取就用默认的名字）
        userRealm.setAuthorizationCachingEnabled(true); //开启授权缓存
        userRealm.setAuthorizationCacheName("authorizationCache");

        return userRealm;
    }


    //配置的是adminRealm
    /*@Bean
    public AdminRealm adminRealm(){
        AdminRealm adminRealm = new AdminRealm();
        logger.info("adminRealm的过滤器执行了.....");
        adminRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return adminRealm;
    }*/

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        //修改密码凭证校验匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为md5
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，比如散列2次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    //下面就是认证器的配置
    /*@Bean
    public CustomModularRealmAuthenticator authenticator(){
        CustomModularRealmAuthenticator authenticator = new CustomModularRealmAuthenticator();
        return authenticator;
    }*/

        @Bean
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
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

        @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

        @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
}