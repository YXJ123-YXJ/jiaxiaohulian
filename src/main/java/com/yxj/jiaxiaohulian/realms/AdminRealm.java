package com.yxj.jiaxiaohulian.realms;

import com.yxj.jiaxiaohulian.config.ShiroConfig;
import com.yxj.jiaxiaohulian.dao.AdminDao;
import com.yxj.jiaxiaohulian.entity.Admin;
import com.yxj.jiaxiaohulian.entity.User;
import com.yxj.jiaxiaohulian.util.shiro.CustomLoginToken;
import com.yxj.jiaxiaohulian.util.shiro.UserType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;


public class AdminRealm /*extends AuthorizingRealm*/ {
    /*private Logger logger= LoggerFactory.getLogger(ShiroConfig.class);
    @Autowired
    private AdminDao adminDao;

    @Override
    public String getName() {
        return UserType.ADMIN;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //第一步：获取用户名
        CustomLoginToken token = (CustomLoginToken)authenticationToken;
        String adminName= (String) token.getPrincipal();
        User user =adminDao.findRolesByAName(adminName).get(0);
        //通过用户名查询用户对象
        if (user==null){
            throw new UnknownAccountException("用户不存在！");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getuName(),user.getuPwd(),ByteSource.Util.bytes(user.getuSalt()),getName());
        logger.info("用户名"+user.getuName());
        logger.info("密码"+user.getuPwd());
        return simpleAuthenticationInfo;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.err.println("------------> admin授权认证 ------------>");
        //获取当前登录的用户信息
        Subject currentAdmin = SecurityUtils.getSubject();
        User u = (User) currentAdmin.getPrincipal();
        //从数据库中获取用户所拥有的角色及权限信息
        User user = adminDao.findRolesByAName(u.getuName()).get(0);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用于存储用户的角色及权限信息
            Collection<String> rolesCollection = new HashSet<>();
//            Collection<String> permissionsCollection = new HashSet<>();
            String role = user.getuRole();
            //通过遍历用户所拥有的角色,来获取其对应的权限信息
            rolesCollection.add(role);
            info.addRoles(rolesCollection); //为用户授予角色
            System.out.println("[roles]------------>" + rolesCollection.toString());
//            System.out.println("[permissions]------------>" + permissionsCollection.toString());
            return info;
        }
        return null;
    }*/
}

