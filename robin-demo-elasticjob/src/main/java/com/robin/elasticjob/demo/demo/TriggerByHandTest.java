/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/9 19:23
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticjob.demo.demo;

import com.dangdang.ddframe.job.api.JobType;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.executor.handler.JobProperties;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.internal.config.LiteJobConfigurationGsonFactory;
import com.dangdang.ddframe.job.lite.internal.storage.JobNodePath;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/9 19:23
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/9 19:23
 */
@Component
public class TriggerByHandTest {

    private final CoordinatorRegistryCenter regCenter;

    public TriggerByHandTest(CoordinatorRegistryCenter regCenter) {
        this.regCenter = regCenter;
    }

    /**
     *  手动触发
     * @param jobName
     * @param serverIp
     */
    public void trigger(final Optional<String> jobName, final Optional<String> serverIp) {
        if (jobName.isPresent()) {
            JobNodePath jobNodePath = new JobNodePath(jobName.get());
            for (String each : regCenter.getChildrenKeys(jobNodePath.getInstancesNodePath())) {
                regCenter.persist(jobNodePath.getInstanceNodePath(each), "TRIGGER");
            }
        }
    }

    /**
     *  更新作业配置信息
     * @param jobSettings
     */
    public void updateJobSettings(final JobSettings jobSettings) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(jobSettings.getJobName()), "jobName can not be empty.");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(jobSettings.getCron()), "cron can not be empty.");
        Preconditions.checkArgument(jobSettings.getShardingTotalCount() > 0, "shardingTotalCount should larger than zero.");

        JobNodePath jobNodePath = new JobNodePath(jobSettings.getJobName());
        regCenter.update(jobNodePath.getConfigNodePath(), LiteJobConfigurationGsonFactory.toJsonForObject(jobSettings));
    }

    /**
     *  获取作业配置
     * @param jobName
     * @return
     */
    public JobSettings getJobSettings(final String jobName) {

        JobSettings result = new JobSettings();
        JobNodePath jobNodePath = new JobNodePath(jobName);
        LiteJobConfiguration liteJobConfig = LiteJobConfigurationGsonFactory.fromJson(regCenter.get(jobNodePath.getConfigNodePath()));
        String jobType = liteJobConfig.getTypeConfig().getJobType().name();
        buildSimpleJobSettings(jobName, result, liteJobConfig);
        if (JobType.DATAFLOW.name().equals(jobType)) {
            buildDataflowJobSettings(result, (DataflowJobConfiguration) liteJobConfig.getTypeConfig());
        }
        if (JobType.SCRIPT.name().equals(jobType)) {
            buildScriptJobSettings(result, (ScriptJobConfiguration) liteJobConfig.getTypeConfig());
        }
        return result;
    }

    private void buildScriptJobSettings(final JobSettings result, final ScriptJobConfiguration config) {
        result.setScriptCommandLine(config.getScriptCommandLine());
    }

    private void buildDataflowJobSettings(final JobSettings result, final DataflowJobConfiguration config) {
        result.setStreamingProcess(config.isStreamingProcess());
    }

    private void buildSimpleJobSettings(final String jobName, final JobSettings result, final LiteJobConfiguration liteJobConfig) {
        result.setJobName(jobName);
        result.setJobType(liteJobConfig.getTypeConfig().getJobType().name());
        result.setJobClass(liteJobConfig.getTypeConfig().getJobClass());
        result.setShardingTotalCount(liteJobConfig.getTypeConfig().getCoreConfig().getShardingTotalCount());
        result.setCron(liteJobConfig.getTypeConfig().getCoreConfig().getCron());
        result.setShardingItemParameters(liteJobConfig.getTypeConfig().getCoreConfig().getShardingItemParameters());
        result.setJobParameter(liteJobConfig.getTypeConfig().getCoreConfig().getJobParameter());
        result.setMonitorExecution(liteJobConfig.isMonitorExecution());
        result.setMaxTimeDiffSeconds(liteJobConfig.getMaxTimeDiffSeconds());
        result.setMonitorPort(liteJobConfig.getMonitorPort());
        result.setFailover(liteJobConfig.getTypeConfig().getCoreConfig().isFailover());
        result.setMisfire(liteJobConfig.getTypeConfig().getCoreConfig().isMisfire());
        result.setJobShardingStrategyClass(liteJobConfig.getJobShardingStrategyClass());
        result.setDescription(liteJobConfig.getTypeConfig().getCoreConfig().getDescription());
        result.setReconcileIntervalMinutes(liteJobConfig.getReconcileIntervalMinutes());
        result.getJobProperties().put(JobProperties.JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER.getKey(),
                liteJobConfig.getTypeConfig().getCoreConfig().getJobProperties().get(JobProperties.JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER));
        result.getJobProperties().put(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey(),
                liteJobConfig.getTypeConfig().getCoreConfig().getJobProperties().get(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER));
    }

}
