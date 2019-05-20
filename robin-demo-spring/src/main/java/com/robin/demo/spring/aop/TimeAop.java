/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/26 16:33
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.spring.aop;

import jdk.nashorn.internal.parser.JSONParser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/26 16:33
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/2/26 16:33
 */
@Aspect
@Component
public class TimeAop implements Ordered{

    @Pointcut("execution(* com.robin.demo.spring.aop.*.*(..))")
    public void pointcut01() {}

//    @Pointcut("execution(* com.robin.demo.spring.*.*(..))")
//    public void pointcut02() {}

    @Around("pointcut01()")
    public Object aroundAop(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("入参"+ Arrays.toString(joinPoint.getArgs()));

        Object res = null;
        try {

            res = joinPoint.proceed();
            System.out.println("输出"+ res);
        }catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    @Override public int getOrder() {
        return 2;
    }
}
