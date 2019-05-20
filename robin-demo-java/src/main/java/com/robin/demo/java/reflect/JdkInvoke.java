/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/21 15:02
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/21 15:02
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/4/21 15:02
 */
public class JdkInvoke implements InvocationHandler{

    private Object target;

    //绑定委托对象，返回代理类
    public Object bind(Object target){//目标对象
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),
                this);
    }

    // 代理对象 执行方法  方法参数
    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke~~~~~~~~");
        for (Object o :args) {
            System.out.println(o);
        }
        Object a = method.invoke(target,args);

        System.out.println("结束");
        System.out.println(a);
        return a;
    }
}
