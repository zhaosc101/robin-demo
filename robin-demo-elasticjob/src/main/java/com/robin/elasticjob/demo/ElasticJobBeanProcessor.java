package com.robin.elasticjob.demo;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.executor.handler.JobProperties;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.robin.elasticjob.demo.api.BaseDataflowJob;
import com.robin.elasticjob.demo.api.BaseScriptJob;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * ElasticJob 处理器
 *
 * @author: wanghongfei[wang_hf@suixingpay.com]
 * @date: 2018年8月1日 上午10:35:09
 * @version: V1.0
 * @review: wanghongfei[wang_hf@suixingpay.com]/2018年8月1日 上午10:35:09
 */
public class ElasticJobBeanProcessor implements BeanPostProcessor {

    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    public ElasticJobBeanProcessor(ZookeeperRegistryCenter zookeeperRegistryCenter) {
        this.zookeeperRegistryCenter = zookeeperRegistryCenter;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 在bean 初化完成后再处理，以保证依赖的实例已被注入
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ElasticJob) {

            if (!(bean instanceof ElasticJobConfig)) {
                throw new IllegalArgumentException("实现 com.dangdang.ddframe.job.api.ElasticJob接口的类必须也要实现" + ElasticJobConfig.class.getName());
            }
            Elasticjob elasticjob = bean.getClass().getAnnotation(Elasticjob.class);
            if (elasticjob == null) {
                throw new IllegalArgumentException("缺失@Elasticjob配置" + ElasticJobConfig.class.getName());
            }
            ElasticJobConfig elasticJobConfig = (ElasticJobConfig) bean;
            JobCoreConfiguration jobCoreConfiguration = createJobCoreConfiguration(elasticJobConfig, elasticjob);
            JobTypeConfiguration jobTypeConfiguration;

            if (bean instanceof SimpleJob) {// simpleJob
                jobTypeConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, bean.getClass().getCanonicalName());
            } else if (bean instanceof BaseDataflowJob) {// dataflwJob
                jobTypeConfiguration = new DataflowJobConfiguration(jobCoreConfiguration, bean.getClass().getCanonicalName(), false);
            } else if (bean instanceof BaseScriptJob) {// scriptJob
                String scriptCommandLine = ((BaseScriptJob) bean).scriptCommandLine();
                jobTypeConfiguration = new ScriptJobConfiguration(jobCoreConfiguration, scriptCommandLine);
            } else {
                throw new IllegalArgumentException("ElasticJob must implement BaseSimpleJob or BaseDataflowJob or BaseScriptJob.");
            }

            LiteJobConfiguration liteJobConfiguration = createLiteJobConfiguration(jobTypeConfiguration, elasticJobConfig, elasticjob);
            ElasticJob elasticJob = (ElasticJob) bean;
            initJobScheduler(elasticJob, elasticJobConfig, liteJobConfiguration, beanName);
        }
        return bean;
    }

    private JobCoreConfiguration createJobCoreConfiguration(ElasticJobConfig elasticJobConfig, Elasticjob elasticjob) {
        Assert.hasLength(elasticjob.jobName(), "elasticjob name is required.");
        Assert.hasLength(elasticjob.cron(), "elasticjob cron is required.");

        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(elasticjob.jobName(), elasticjob.cron(), elasticjob.shardingTotalCount())
                .shardingItemParameters(elasticjob.shardingItemParameters())
                .jobParameter(elasticjob.jobParameter())
                .failover(elasticjob.failover())
                .misfire(elasticjob.misfire())
                .description(elasticjob.description())
                .jobProperties(JobProperties.JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER.getKey(), elasticJobConfig.executorServiceHandler().getCanonicalName())
                .jobProperties(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey(), elasticJobConfig.jobExceptionHandler().getCanonicalName())
                .build();

        return jobCoreConfiguration;
    }


    private LiteJobConfiguration createLiteJobConfiguration(JobTypeConfiguration jobTypeConfiguration, ElasticJobConfig elasticJobConfig, Elasticjob elasticjob) {
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(jobTypeConfiguration)
                .monitorExecution(elasticjob.monitorExecution())
                .maxTimeDiffSeconds(elasticjob.maxTimeDiffSeconds())
                .monitorPort(elasticjob.monitorPort())
                .jobShardingStrategyClass(elasticJobConfig.jobShardingStrategyClass().getCanonicalName())
                .reconcileIntervalMinutes(elasticjob.reconcileIntervalMinutes())
                .disabled(elasticjob.disabled())
                .overwrite(elasticjob.overwrite())
                .build();
        return liteJobConfiguration;
    }

    private void initJobScheduler(ElasticJob elasticJob, ElasticJobConfig elasticJobConfig, LiteJobConfiguration liteJobConfiguration, String beanName) {
        JobEventRdbConfiguration jobEventConfig = null;
        if (null != elasticJobConfig.eventTraceRdbDataSource()) {
            jobEventConfig = new JobEventRdbConfiguration(elasticJobConfig.eventTraceRdbDataSource());
        }
        List<ElasticJobListener> listeners = new ArrayList<>(2);
        if (null != elasticJobConfig.listener()) {
            listeners.add(elasticJobConfig.listener());
        }
        if (null != elasticJobConfig.distributedListener()) {
            listeners.add(elasticJobConfig.distributedListener());
        }
        ElasticJobListener[] elasticJobListeners = new ElasticJobListener[listeners.size()];
        if (!listeners.isEmpty()) {
            elasticJobListeners = listeners.toArray(elasticJobListeners);
        }

        SpringJobScheduler springJobScheduler = null;
        if (null != jobEventConfig) {
            springJobScheduler = new SpringJobScheduler(elasticJob, zookeeperRegistryCenter, liteJobConfiguration, jobEventConfig, elasticJobListeners);
        } else {
            springJobScheduler = new SpringJobScheduler(elasticJob, zookeeperRegistryCenter, liteJobConfiguration, elasticJobListeners);
        }

        springJobScheduler.init();
    }

}
