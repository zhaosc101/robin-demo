/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/8 22:27
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticjob.demo.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.robin.elasticjob.demo.ElasticJobBeanProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;



/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/8 22:27
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/8 22:27
 */
@EnableConfigurationProperties(ZookeeperProperties.class)
@Configuration
public class RegisterConfiguration {


    private ZookeeperProperties zookeeperProperties;

    public RegisterConfiguration(ZookeeperProperties zookeeperProperties) {
        this.zookeeperProperties = zookeeperProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public ZookeeperConfiguration zookeeperConfiguration() {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(zookeeperProperties.getServers(),zookeeperProperties.getNamespace());

        if(Objects.nonNull(zookeeperProperties.getDigest()) && zookeeperProperties.getDigest().length() > 0) {
            zkConfig.setDigest(zkConfig.getDigest());
        }

        zkConfig.setBaseSleepTimeMilliseconds(zkConfig.getBaseSleepTimeMilliseconds());
        zkConfig.setConnectionTimeoutMilliseconds(zkConfig.getConnectionTimeoutMilliseconds());
        zkConfig.setMaxRetries(zkConfig.getMaxRetries());
        zkConfig.setMaxSleepTimeMilliseconds(zkConfig.getMaxSleepTimeMilliseconds());
        zkConfig.setSessionTimeoutMilliseconds(zkConfig.getSessionTimeoutMilliseconds());

        return zkConfig;

    }

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    @ConditionalOnBean(ZookeeperConfiguration.class)
    public ZookeeperRegistryCenter zookeeperRegistryCenter(ZookeeperConfiguration zookeeperConfiguration) {

        return new ZookeeperRegistryCenter(zookeeperConfiguration);

    }

    @Bean
    @ConditionalOnBean(ZookeeperRegistryCenter.class)
    public ElasticJobBeanProcessor annotationBeanProcessor(ZookeeperRegistryCenter zookeeperRegistryCenter) {
        ElasticJobBeanProcessor annotationBeanProcessor = new ElasticJobBeanProcessor(zookeeperRegistryCenter);
        return annotationBeanProcessor;
    }


}
