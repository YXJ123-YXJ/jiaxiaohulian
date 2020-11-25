package com.yxj.jiaxiaohulian.util.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomModularRealmAuthenticator extends ModularRealmAuthenticator {

    /**
     * 想干一件事
     *    就是通过传入数据的类型  来选择使用哪一个Realm
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        //做Realm的一个校验   判断getRealms()是否返回为空
        assertRealmsConfigured();
        //获取前端传递过来的token
        CustomLoginToken customLoginToken=(CustomLoginToken)authenticationToken;
        //现在就可以获取这个登陆的类型了
        String loginType = customLoginToken.getLoginType();  //  登陆类型   1：user   admin
        //获取所有的realms()
        Collection<Realm> realms = getRealms();
        //登陆类型对应的所有realm全部获取到
        List<Realm> typeRealms=new ArrayList<>();
        System.out.println("-----》》》"+loginType);

        for (Realm realm:realms){
            //realm类型和现在登陆的类型做一个对比

            if(realm.getName().equals(loginType)){   //就能分开这两个realm
                System.out.println("-----》》》"+realm.getName());
                typeRealms.add(realm);
            }
        }

        System.out.println("===================typeRealms.size() ==================" + typeRealms.size());
        if(typeRealms.size()==1){
            System.out.println(typeRealms.get(0));
            return doSingleRealmAuthentication(typeRealms.get(0),customLoginToken);
        }else{
            return doMultiRealmAuthentication(typeRealms,customLoginToken);
        }
    }
}
