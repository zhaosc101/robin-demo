/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/11 10:28
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.singleton;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/11 10:28
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/11 10:28
 */
public class Singleton02 {

    private Singleton02(){};
    private static class HodlerClass {
        private static Singleton02 singleton02 = new Singleton02();
    }

    public static Singleton02 getSingleton() {
       return HodlerClass.singleton02;
    }

    public static void main(String[] args) {
        Singleton02 sing = Singleton02.getSingleton();
        Singleton02 sing1 = Singleton02.getSingleton();
        if(sing==sing1) {
            System.out.println("----");
        }
    }
}
