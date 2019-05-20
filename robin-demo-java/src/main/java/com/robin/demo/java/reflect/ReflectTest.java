/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/13 14:46
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/13 14:46
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/13 14:46
 */
public class ReflectTest {

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException,
            InvocationTargetException {

        Class clazz = Class.forName("com.robin.demo.java.reflect.People");
        Field[] fields = clazz.getFields();
        Method method = clazz.getMethod("setName",String.class);
        People o  = (People)clazz.newInstance();
        method.invoke(o,"zhaosc");
        System.out.println(o.getName());
    }
}
