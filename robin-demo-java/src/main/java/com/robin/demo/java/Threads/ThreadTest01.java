/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/26 15:15
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.Threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/26 15:15
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/26 15:15
 */

public class ThreadTest01 {

    public static void main(String[] args) throws InterruptedException {
//        AtomicInteger
        ThreadTest01 threadTest01 = new ThreadTest01();
        LoggerWidget aa = threadTest01.new LoggerWidget();
//        aa.doSomething();
//
//
        new Thread(new Runnable() {
            @Override public void run() {
                Widget widget = threadTest01.new Widget();
                widget.doSomething();
            }
        }).start();
        Thread.sleep(100);
        aa.dosome();
    }

    class Widget {

        public synchronized void doSomething() {
            System.out.println("父类Widget-doSomething");
            try {

                Thread.sleep(10000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("ddddend");
        }
    }

    class LoggerWidget extends Widget {

        public synchronized void dosome() {
            System.out.println("子锁");
        }
         public void doSomething1() {
            System.out.println("ffff");
            super.doSomething();
            System.out.println("ffffend");
        }
    }
}
