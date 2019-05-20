/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/14 17:40
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.lock;

import org.junit.Test;

import java.util.Collections;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/14 17:40
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/14 17:40
 */

public class ReetrantReadWriteLockTest {


    @Test
    public void test() {

//        Collections.synchronizedList();
        System.out.println("111");
    }

    public class Count {

        private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void get() {
            readWriteLock.readLock().lock();

        }
    }
}
