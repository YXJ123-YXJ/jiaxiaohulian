package com.yxj.jiaxiaohulian.realms;


import com.yxj.jiaxiaohulian.dao.AdminDao;
import com.yxj.jiaxiaohulian.dao.LoginDao;
import com.yxj.jiaxiaohulian.entity.Admin;
import com.yxj.jiaxiaohulian.entity.User;
import com.yxj.jiaxiaohulian.util.ApplicationContextUtils;
import com.yxj.jiaxiaohulian.util.shiro.CustomLoginToken;
import com.yxj.jiaxiaohulian.util.shiro.UserType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

//自定义的UserRealm
public class UserRealm2 /*extends AuthorizingRealm*/ {

   /* //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证："+primaryPrincipal);
        //根据主身份信息获取角色 和 权限信息
        //在工厂中获取service对象
        LoginDao loginDao = (LoginDao) ApplicationContextUtils.getBean("loginDao");
        User user = loginDao.findRolesByUserName(primaryPrincipal);
        //授权角色信息
        if (!ObjectUtils.isEmpty(user.getuRole())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole(user.getuRole());
            return simpleAuthorizationInfo;
        }
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("=============");
        //根据身份信息
        String principal = (String) token.getPrincipal();
        //在工厂中获取service对象
        LoginDao loginDao = (LoginDao) ApplicationContextUtils.getBean("loginDao");

        User user = loginDao.findRolesByUserName(principal);
        System.out.println("当前用户"+principal);
        System.out.println("用户user："+user);

        if (!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getuName(),user.getuPwd(), ByteSource.Util.bytes(user.getuSalt()),this.getName());
        }
        return null;
    }*/






    /*@Override
    public String getName() {
        return UserType.USER;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取身份信息
        Subject subject = SecurityUtils.getSubject();

        System.out.println("真实用户类型吗："+principals.getRealmNames());
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("调用授权验证："+primaryPrincipal);
        //根据主身份信息获取角色 和 权限信息
        //在工厂中获取dao对象
        LoginDao loginDao = (LoginDao) ApplicationContextUtils.getBean("loginDao");
        User user = loginDao.findRolesByUserName(primaryPrincipal);
        //授权角色信息
        if (!ObjectUtils.isEmpty(user.getuRole())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole(user.getuRole());
            return simpleAuthorizationInfo;
        }
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        CustomLoginToken token = (CustomLoginToken) authcToken;
        //在工厂中获取dao对象
        System.out.println("用户类型："+token.getLoginType());
        LoginDao loginDao = (LoginDao) ApplicationContextUtils.getBean("loginDao");
        AdminDao adminDao = (AdminDao) ApplicationContextUtils.getBean("adminDao");
        if ("user".equals(token.getLoginType())){
            User user = loginDao.findRolesByUserName(token.getUsername());
            if (!ObjectUtils.isEmpty(user)){
                return new SimpleAuthenticationInfo(user.getuName(),user.getuPwd(), ByteSource.Util.bytes(user.getuSalt()),this.getName());
            }
        }
        if ("admin".equals(token.getLoginType())){
            Admin admin = adminDao.findRolesByAName(token.getUsername()).get(0);
            System.out.println(admin);
            if (!ObjectUtils.isEmpty(admin)){
                return new SimpleAuthenticationInfo(admin.getaName(),admin.getaPwd(), ByteSource.Util.bytes(admin.getaSalt()),this.getName());
            }
        }

        return null;
    }


    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    @Override
    protected void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

*/

}
