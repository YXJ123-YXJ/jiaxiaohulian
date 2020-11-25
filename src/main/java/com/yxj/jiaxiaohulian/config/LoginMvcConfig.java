package com.yxj.jiaxiaohulian.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@Configuration
public class LoginMvcConfig /*implements WebMvcConfigurer*/ {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册TestInterceptor拦截器
//        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
//        registration.addPathPatterns("/**");                      //所有路径都被拦截
//        registration.excludePathPatterns(                        //添加不拦截路径
////                "/**/*.*",            //登录
//                "/userLogin.html",            //登录
//                "/login.do",            //登录
////                "/**/*.html",            //html静态资源
//                "/**/*.js",              //js静态资源
//                "/**/*.css",             //css静态资源
//                "/**/*.woff",
//                "/**/*.ttf",
//                "/**/*.mp4",
//                "/bg/**/*.*",
//                "/img/**/*.*",
//                "/error/**/*.*",
//                "/userRegister.html",
//                "/adminLogin.html",
//                "/admin/adminLogin.do",
//                "/userXieYi.html",
//                //注册页面下拉列表的动态显示
//                "/findAllUtName.do",
//                "/findYuanXi.do"
//        );
//    }
}
