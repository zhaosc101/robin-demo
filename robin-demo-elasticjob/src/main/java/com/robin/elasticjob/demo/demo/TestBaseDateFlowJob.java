/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/9 14:55
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticjob.demo.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.robin.elasticjob.demo.Elasticjob;
import com.robin.elasticjob.demo.api.BaseDataflowJob;
import com.robin.elasticjob.demo.api.BaseSimpleJob;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/9 14:55
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/9 14:55
 */
@Elasticjob(jobName = "TestBaseDateFlowJob", cron = "0/15 * * * * ?", overwrite = true,shardingItemParameters = "0=a,1=b,2=c")
@Component
public class TestBaseDateFlowJob implements BaseDataflowJob<String> {

    AtomicInteger count = new AtomicInteger();

    @Override public ElasticJobListener listener() {
        return new DemoListener();
    }

    @Override public List<String> fetchData(ShardingContext shardingContext) {
        System.out.println("TestBaseDateFlowJob execute {}"+shardingContext.getShardingParameter());
        System.out.println("count"+count.incrementAndGet());

        if(count.get() <10 ) {

            return Arrays.asList("1","2");
        }

        return null;
    }

    @Override public void processData(ShardingContext shardingContext, List data) {

        System.out.println("processData"+data);
    }

}
