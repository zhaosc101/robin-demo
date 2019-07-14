package com.robin.elasticjob.demo.api;

import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.robin.elasticjob.demo.ElasticJobConfig;

/**
 * 数据流分布式作业基类
 *
 * @param <T> 数据类型
 */
public interface BaseDataflowJob<T> extends DataflowJob, ElasticJobConfig {

    /**
     *
     * @return
     */
    default boolean streamingProcess() {
        return false;
    }
}
