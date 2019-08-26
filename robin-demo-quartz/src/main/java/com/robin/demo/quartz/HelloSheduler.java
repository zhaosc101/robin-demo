package com.robin.demo.quartz;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

public class HelloSheduler {

    public static void main(String[] args) throws SchedulerException {

        //创建一个jobdetail 实例 将改实例与hellojob 绑定
        JobDetail detail = JobBuilder.newJob(HelloJob.class).withIdentity("hellojob","group1").build();

        //创建一个Trigger 实例定义改job 立即执行 并且每个两秒钟重复执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger","group1").startNow().
                withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).
                build();

        //执行
        SchedulerFactory factory = new StdSchedulerFactory();

        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(detail,trigger);
        scheduler.start();
    }
}
