/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/25 10:42
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/25 10:42
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/4/25 10:42
 */
public class JvmTest {

    public static void main(String[] args) {

        directMemoryOOM();
    }

    /**
     * Xmx20M -XX:MaxDirectMemorySize=10M
     */
    private static final int _1M = 1024*1024;
    private static void directMemoryOOM() {
        try {
            Field unsalf = Unsafe.class.getDeclaredFields()[0];
            unsalf.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsalf.get(null);
            while (true) {
                unsafe.allocateMemory(_1M);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
