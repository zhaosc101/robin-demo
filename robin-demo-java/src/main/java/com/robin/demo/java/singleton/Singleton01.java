/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/11 9:25
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.singleton;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/11 9:25
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/11 9:25
 */
public class Singleton01 {
    private static Singleton01 singleton = new Singleton01();

    private Singleton01(){}

    public static Singleton01 getSingleton() {
        return singleton;
    }

    public static void main(String[] args) {
        Singleton01 singleton01 = Singleton01.getSingleton();
        Singleton01 singleton = Singleton01.getSingleton();

        System.out.println();
    }
}
