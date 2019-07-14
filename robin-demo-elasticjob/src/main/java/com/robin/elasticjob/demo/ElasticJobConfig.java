package com.robin.elasticjob.demo;

import com.dangdang.ddframe.job.executor.handler.ExecutorServiceHandler;
import com.dangdang.ddframe.job.executor.handler.JobExceptionHandler;
import com.dangdang.ddframe.job.executor.handler.impl.DefaultExecutorServiceHandler;
import com.dangdang.ddframe.job.executor.handler.impl.DefaultJobExceptionHandler;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.api.strategy.JobShardingStrategy;
import com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy;

import javax.sql.DataSource;

/**
 * ElasticJob 配置
 *
 * @author: wanghongfei[wang_hf@suixingpay.com]
 * @date: 2018年8月1日 上午10:35:09
 * @version: V1.0
 * @review: wanghongfei[wang_hf@suixingpay.com]/2018年8月1日 上午10:35:09
 */
public interface ElasticJobConfig {

    /**
     * 作业分片策略实现类全路径,默认使用平均分配策略
     *
     * @return
     */
    default Class<? extends JobShardingStrategy> jobShardingStrategyClass() {
        return AverageAllocationJobShardingStrategy.class;
    }

    /**
     * 作业事件追踪的数据源Bean引用
     *
     * @return
     */
    default DataSource eventTraceRdbDataSource() {
        return null;
    }

    /**
     * 前置后置任务监听实现类，需实现ElasticJobListener接口
     *
     * @return
     */
    default ElasticJobListener listener() {
        return null;
    }

    /**
     * @return
     * @see AbstractDistributeOnceElasticJobListener
     * 前置后置任务分布式监听实现类，需继承AbstractDistributeOnceElasticJobListener 类
     */
    default AbstractDistributeOnceElasticJobListener distributedListener() {
        return null;
    }

    /**
     * 自定义异常处理类
     *
     * @return
     */
    default Class<? extends JobExceptionHandler> jobExceptionHandler() {
        return DefaultJobExceptionHandler.class;
    }

    /**
     * 自定义业务处理线程池
     *
     * @return
     */
    default Class<? extends ExecutorServiceHandler> executorServiceHandler() {
        return DefaultExecutorServiceHandler.class;
    }
}
