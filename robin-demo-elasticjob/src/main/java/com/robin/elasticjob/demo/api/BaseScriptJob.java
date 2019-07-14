package com.robin.elasticjob.demo.api;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.robin.elasticjob.demo.ElasticJobConfig;

/**
 * 脚本作业基类
 */
public interface BaseScriptJob extends ElasticJob, ElasticJobConfig {
    String scriptCommandLine();
}
