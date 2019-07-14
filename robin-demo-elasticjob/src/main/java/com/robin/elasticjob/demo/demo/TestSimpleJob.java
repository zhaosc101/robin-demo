/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/9 14:55
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticjob.demo.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.robin.elasticjob.demo.Elasticjob;
import com.robin.elasticjob.demo.api.BaseSimpleJob;
import org.springframework.stereotype.Component;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/9 14:55
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/9 14:55
 */
@Elasticjob(jobName = "authDelayCheckJob", cron = "0/15 * * * * ?", overwrite = true,shardingItemParameters = "0=a,1=b,2=c")
@Component
public class TestSimpleJob implements BaseSimpleJob{

    @Override public void execute(ShardingContext shardingContext) {
        System.out.println("TestSimpleJob execute {}"+shardingContext.getShardingParameter());

    }

    @Override public ElasticJobListener listener() {
        return new DemoListener();
    }

}
