/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/18 16:41
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.thread;

import java.util.concurrent.*;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/18 16:41
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/2/18 16:41
 */
public class ThreadTest {

    //最大线程数一般设为2N+1最好，N是CPU核数
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors()*2+1,
            Runtime.getRuntime().availableProcessors()*3,
            60L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100)
            );

    ExecutorService mCachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
