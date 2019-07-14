/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/8 22:18
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticjob.demo.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/8 22:18
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/8 22:18
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "robin.elasticjob.zk")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ZookeeperProperties {



    private final Environment env;

    /**
     * 连接Zookeeper服务器的列表包括IP地址和端口号，多个地址用逗号分隔
     * 如: host1:2181,host2:2181,host3:2181
     */
    private String servers;

    /**
     * Zookeeper的命名空间
     */
    private String namespace;

    /**
     * 等待重试的间隔时间的初始值 单位：毫秒
     */
    private int baseSleepTimeMilliseconds = 1000;

    /**
     * 等待重试的间隔时间的最大值
     * 单位：毫秒
     */
    private int maxSleepTimeMilliseconds = 3000;

    /**
     * 最大重试次数
     */
    private int maxRetries = 3;

    /**
     * 会话超时时间
     * 单位：毫秒
     */
    private int sessionTimeoutMilliseconds = 60000;

    /**
     * 连接超时时间
     * 单位：毫秒
     */
    private int connectionTimeoutMilliseconds = 15000;

    /**
     * 连接Zookeeper的权限令牌
     * 缺省为不需要权限验证
     */
    private String digest;


    @PostConstruct
    public void init() {
        if (null != env) {
            if (null == namespace || namespace.trim().isEmpty()) {
                String applicationName = env.getProperty("spring.application.name");
                if (null != applicationName && !applicationName.trim().isEmpty()) {
                    this.namespace = applicationName;
                }
            }
        }
    }


}
