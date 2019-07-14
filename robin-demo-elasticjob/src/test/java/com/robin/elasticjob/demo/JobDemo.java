/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/8 21:34
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticjob.demo;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.executor.handler.JobProperties;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/8 21:34
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/8 21:34
 */
public class JobDemo {

    public static void main(String[] args) {
        System.out.println(JobDemo.class.getCanonicalName());
        new JobScheduler(createRegistryCenter(),createJobConfiguration()).init();
    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter rgCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration(TestZookeeper.ADDRESS,
                "robin-demo"));

        rgCenter.init();

        return rgCenter;
    }


    private static LiteJobConfiguration createJobConfiguration() {

        // 创建作业配置
        JobCoreConfiguration jobCore = JobCoreConfiguration.newBuilder("elastic-job-demo",
                "0/15 * * * * ?",10).build();

        JobTypeConfiguration jobType = new SimpleJobConfiguration(jobCore,JobDemo.class.getCanonicalName());

        LiteJobConfiguration configuration = LiteJobConfiguration.newBuilder(jobType).build();
        return configuration;
    }
}
