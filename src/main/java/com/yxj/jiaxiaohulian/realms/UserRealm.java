package com.yxj.jiaxiaohulian.realms;


import com.yxj.jiaxiaohulian.config.ShiroConfig;
import com.yxj.jiaxiaohulian.dao.LoginDao;
import com.yxj.jiaxiaohulian.entity.User;
import com.yxj.jiaxiaohulian.util.ApplicationContextUtils;
import com.yxj.jiaxiaohulian.util.cache.MyByteSource;
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


public class UserRealm extends AuthorizingRealm {
    private Logger logger= LoggerFactory.getLogger(ShiroConfig.class);
    /*@Autowired
    private LoginDao loginDao;*/

    @Override
    public String getName() {
        return UserType.USER;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //第一步：获取用户名
//        CustomLoginToken token = (CustomLoginToken)authenticationToken;
        String userName= (String) token.getPrincipal();
        LoginDao loginDao = (LoginDao) ApplicationContextUtils.getBean("loginDao");
        User user =loginDao.findRolesByUserName(userName);
        //通过用户名查询用户对象
        if (user==null){
            throw new UnknownAccountException("用户不存在！");
        }
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getuPwd(), ByteSource.Util.bytes(user.getuSalt()),getName());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getuPwd(), MyByteSource.Util.bytes(user.getuSalt()),getName());
        logger.info("用户名"+user.getuName());
        logger.info("密码"+user.getuPwd());
        return simpleAuthenticationInfo;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.err.println("------------> User授权认证 ------------>");
        //获取当前登录的用户信息
        Subject currentUser = SecurityUtils.getSubject();
        User u = (User) currentUser.getPrincipal();
        LoginDao loginDao = (LoginDao) ApplicationContextUtils.getBean("loginDao");
        //从数据库中获取用户所拥有的角色及权限信息
        User user = loginDao.findRolesByUserName(u.getuName());
        System.out.println("user =================" + user);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用于存储用户的角色及权限信息
//            Collection<String> rolesCollection = new HashSet<>();
//            Collection<String> permissionsCollection = new HashSet<>();
            String role = user.getuRole(); //获取用户Role的Set集合
            //通过遍历用户所拥有的角色,来获取其对应的权限信息
//            rolesCollection.add(role);
            info.addRole(role); //为用户授予角色
//            System.out.println("[roles]------------>" + rolesCollection.toString());
//            System.out.println("[permissions]------------>" + permissionsCollection.toString());
            return info;
        }
        return null;
    }

    /**
     * 清除缓存
     */
    public void clearCache() {
        logger.info("清除shiro缓存数据");
        Subject subject=SecurityUtils.getSubject();
        // 调用子类去清理缓存
        super.clearCache(subject.getPrincipals());
    }
}
