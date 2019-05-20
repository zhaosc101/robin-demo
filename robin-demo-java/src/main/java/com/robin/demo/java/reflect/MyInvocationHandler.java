/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/14 11:56
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/14 11:56
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/14 11:56
 */
public class MyInvocationHandler implements InvocationHandler{

    //目标对象
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     *  执行目标对象的方法
     * @param proxy 指代我们所代理的那个真实对象
     * @param method 指代的是我们所要调用真实对象的某个方法的Method对象
     * @param args 指代的是调用真实对象某个方法时接受的参数
     * @return
     * @throws Throwable
     */
    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object o = method.invoke(target,args);

        return o;
    }

    public Object getProxy() {

        return Proxy.newProxyInstance(target.getClass().getClassLoader()
                ,target.getClass().getInterfaces(),this);
    }
}



