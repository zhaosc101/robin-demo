/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/6 15:14
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticjob.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/6 15:14
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/6 15:14
 */
@SpringBootApplication
public class ElasticJobApplication {

    public static void main(String[] args) {

        SpringApplication.run(ElasticJobApplication.class,args);

    }

}
