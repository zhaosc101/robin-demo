/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/10 9:41
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticjob.demo;

import com.dangdang.ddframe.job.reg.exception.RegExceptionHandler;
import com.google.common.base.Charsets;
import com.robin.elasticjob.demo.config.ZookeeperProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;

import java.util.concurrent.TimeUnit;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/10 9:41
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/10 9:41
 */
public class CuratorTest {

    private static CuratorFramework client;

    public static void main(String[] args) throws Exception {
        CuratorTest test = new CuratorTest();
        test.init();

//        client.create().forPath("/curatoer1","13221".getBytes());
        System.out.println(new String(client.getData().forPath("/curatoer1"), Charsets.UTF_8));
    }

    public void init() {

        ZookeeperProperties zkConfig = new ZookeeperProperties(null);
        zkConfig.setServers("192.168.109.144:2181,192.168.109.144:2182,192.168.109.144:2183");
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString(zkConfig.getServers())
                .retryPolicy(new ExponentialBackoffRetry(zkConfig.getBaseSleepTimeMilliseconds(), zkConfig.getMaxRetries(), zkConfig.getMaxSleepTimeMilliseconds()))
                .namespace(zkConfig.getNamespace());
        if (0 != zkConfig.getSessionTimeoutMilliseconds()) {
            builder.sessionTimeoutMs(zkConfig.getSessionTimeoutMilliseconds());
        }
        if (0 != zkConfig.getConnectionTimeoutMilliseconds()) {
            builder.connectionTimeoutMs(zkConfig.getConnectionTimeoutMilliseconds());
        }
        client = builder.build();
        client.start();

        try {
            if (!client.blockUntilConnected(zkConfig.getMaxSleepTimeMilliseconds() * zkConfig.getMaxRetries(), TimeUnit.MILLISECONDS)) {
                client.close();
                throw new KeeperException.OperationTimeoutException();
            }
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
            RegExceptionHandler.handleException(ex);
        }
    }

}
