package com.robin.demo.java.interact;

/**
 * {TODO}
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/6 13:19
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/6 13:19
 */
public interface Iinteract {

    void myDefault1();

    static void myDefault() {
        System.out.println("myDefault");
    }

    default Object myDefault02() {
        System.out.println("myDefault02");
        return"dd";
    }
}
