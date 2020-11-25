package com.yxj.jiaxiaohulian.aspectLog;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class AspectLog {

    private Logger logger= LoggerFactory.getLogger(AspectLog.class);

    //execution (* com.yxj.jiaxiaohulian.dao.*.*(..)) 任意修饰符的任意返回值  com.yxj.jiaxiaohulian.dao下的任意类的任意方法的任意参数
    @Pointcut("execution (* com.yxj.jiaxiaohulian.dao.*.*(..))")
    public void pointCutDao(){}
    @Pointcut("execution (* com.yxj.jiaxiaohulian.service.*.*(..))")
    public void pointCutService(){}
    @Pointcut("execution (* com.yxj.jiaxiaohulian.controller.*.*(..))")
    public void pointCutController(){}

    //以下两种注解方式等效  和@Pointcut标记的方法名对应
    @Before("pointCutDao()")
//    @Before("execution (* com.yxj.jiaxiaohulian.dao.*.*(..))")
    public void doBeforeDao(JoinPoint jp){
        logger.info("前置 --> dao 方法名:"+jp.getSignature().getName()+" ");
        logger.info("参数:"+ Arrays.toString(jp.getArgs()));
    }

    //以下两种注解方式等效
   /* @After("pointCutDao()")
//    @After("execution (* com.yxj.jiaxiaohulian.dao.*.*(..))")
    public void doAfterDao(JoinPoint jp){
        System.out.print("后置 --> dao 方法名:"+jp.getSignature().getName()+" ");
        System.out.println("参数:"+ Arrays.toString(jp.getArgs()));
    }*/

    //以下两种注解方式等效
    @Before("pointCutService()")
//    @Before("execution (* com.yxj.jiaxiaohulian.service.*.*(..))")
    public void doBeforeService(JoinPoint jp){
        logger.info("前置 --> service 方法名:"+jp.getSignature().getName()+" ");
        logger.info("参数:"+ Arrays.toString(jp.getArgs()));
    }

    //以下两种注解方式等效
    /*@After("pointCutService()")
//    @After("execution (* com.yxj.jiaxiaohulian.service.*.*(..))")
    public void doAfterService(JoinPoint jp){
        System.out.print("后置 --> service 方法名:"+jp.getSignature().getName()+" ");
        System.out.println("参数:"+ Arrays.toString(jp.getArgs()));
    }*/

    //以下两种注解方式等效
    @Before("pointCutController()")
//    @Before("execution (* com.yxj.jiaxiaohulian.controller.*.*(..))")
    public void doBeforeController(JoinPoint jp){
        logger.info("前置 --> Controller 方法名:"+jp.getSignature().getName()+" ");
        logger.info("参数:"+ Arrays.toString(jp.getArgs()));
    }

    //以下两种注解方式等效
    /*@After("pointCutController()")
//    @After("execution (* com.yxj.jiaxiaohulian.controller.*.*(..))")
    public void doAfterController(JoinPoint jp){
        System.out.print("后置 --> Controller 方法名:"+jp.getSignature().getName()+" ");
        System.out.println("参数:"+ Arrays.toString(jp.getArgs()));
    }*/


}
