package com.yxj.jiaxiaohulian.util.shiro;


public class CustomModularRealmAuthenticator1 /*extends ModularRealmAuthenticator*/ {

    /*private Map<String, Object> definedRealms;

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 判断getRealms()是否返回为空
        assertRealmsConfigured();
        // 强制转换回自定义的CustomizedToken
        CustomLoginToken token = (CustomLoginToken) authenticationToken;
        // 找到当前登录人的登录类型
        String loginType = token.getLoginType();
        // 所有Realm
        Collection<Realm> realms = getRealms();
        // 找到登录类型对应的指定Realm
        Collection<Realm> typeRealms = new ArrayList<Realm>();
        for (Realm realm : realms) {
            if (realm.getName().toLowerCase().contains(loginType))
                typeRealms.add(realm);
        }

        // 判断是单Realm还是多Realm
        if (typeRealms.size() == 1)
            return doSingleRealmAuthentication(typeRealms.iterator().next(), token);
        else
            return doMultiRealmAuthentication(typeRealms, token);
    }


    *//**
     * 判断realm是否为空
     *//*
    @Override
    protected void assertRealmsConfigured() throws IllegalStateException {
        this.definedRealms = this.getDefinedRealms();
        if (CollectionUtils.isEmpty(this.definedRealms)) {
            throw new ShiroException("值传递错误!");
        }
    }

    public Map<String, Object> getDefinedRealms() {
        return this.definedRealms;
    }

    public void setDefinedRealms(Map<String, Object> definedRealms) {
        this.definedRealms = definedRealms;
    }*/
}
