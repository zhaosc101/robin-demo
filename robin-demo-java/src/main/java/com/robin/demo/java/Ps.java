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

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 100000) ? 100000 : n + 1;
    }

    public static void main(String[] args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        System.out.println(tableSizeFor(10));
        System.out.println(tableSizeFor(16));
        System.out.println(tableSizeFor(30));

    }
}
