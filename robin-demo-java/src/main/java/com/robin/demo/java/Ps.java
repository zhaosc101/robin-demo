/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/11 17:31
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/11 17:31
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/11 17:31
 */
public class Ps {

    public class Man <T> {

        public void test() {

        }
    }

    public class People {

    }

    public static void main(String[] args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Type mySuperClass = new ArrayList<String>() {
        }.getClass().getGenericSuperclass();

        Type type = ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
        System.out.println(type);

        ArrayList<String> list = new ArrayList<String>();
        ArrayList list1 = list;
        list1.add(12);
        System.out.println(list1.get(0));

        ArrayList<String> list2 = new ArrayList<String>();
        Class clazz = list2.getClass();
        Method m = clazz.getMethod("add", Object.class);
        m.invoke(list, 100);
    }
}
