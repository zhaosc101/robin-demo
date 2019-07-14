package com.robin.elasticjob.demo.api;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.robin.elasticjob.demo.ElasticJobConfig;

/**
 * 简单分布式作业基类
 */
public interface BaseSimpleJob extends SimpleJob, ElasticJobConfig {

}
