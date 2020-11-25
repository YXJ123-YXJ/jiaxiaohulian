package com.yxj.jiaxiaohulian.util.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Description:自定义shiro-token重写类,用于多类型用户校验
 */
public class CustomLoginToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 2020457391511655213L;

    private String loginType;

    public CustomLoginToken() {}

    public CustomLoginToken(final String username,final String password,final String loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}
