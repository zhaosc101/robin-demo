/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/6 18:22
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticjob.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/6 18:22
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/6 18:22
 */
public class TestZookeeper implements Watcher{

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override public void process(WatchedEvent event) {
        System.out.println("receive the event:"+event);
    }

    public static final String ADDRESS = "192.168.109.144:2181,192.168.109.144:2182,192.168.109.144:2183";
    public static void main(String[] args) throws IOException {

        ZooKeeper zooKeeper = new ZooKeeper(ADDRESS, 5000, new TestZookeeper());
        System.out.println(zooKeeper.getState());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zookeeper session established");
    }
}
