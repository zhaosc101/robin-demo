/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/9 14:58
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticjob.demo.demo;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import lombok.extern.slf4j.Slf4j;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/9 14:58
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/9 14:58
 */
@Slf4j
public class DemoListener implements ElasticJobListener{

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {

        log.info("DemoListener beforeJobExecuted....");
        log.info("jobName[{}],jobParamter[{}]",shardingContexts.getJobName(),shardingContexts.getJobParameter());
    }


    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        log.info("DemoListener afterJobExecuted....");
    }
}
