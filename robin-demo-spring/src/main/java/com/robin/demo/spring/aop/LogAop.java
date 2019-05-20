/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/26 14:55
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.spring.aop;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/26 14:55
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/2/26 14:55
 */
public class LogAop {

    public void beforeLog(JoinPoint point) throws Throwable {
        System.out.println("前置 参数"+point.getArgs());
    }

    public void afterLog(JoinPoint point) throws Throwable {

        System.out.println("后置");
    }

    public void afterThrowing(JoinPoint joinPoint,Throwable throwable){
        System.out.println("AOP AfterThrowing Advice..." + throwable);
    }

    public void returning(JoinPoint joinPoint,Object o){
        System.out.println("方法返回值" + o);
    }
}
