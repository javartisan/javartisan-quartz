package com.javartisan.quartz;

import org.quartz.*;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

// 不允许该Job的多个实例同时执行
@DisallowConcurrentExecution
// 执行完Job之后持久化新的JobData
@PersistJobDataAfterExecution
public class HelloQuartzJob implements Job {

    public HelloQuartzJob() {
        System.out.println("在 org.quartz.spi.JobFactory.newJob()方法中已经完成Job实例的反射创建！");
    }

    private String uuid = UUID.randomUUID().toString();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("JobDetail and Trigger MergedJobDataMap = " + context.getMergedJobDataMap());
        System.out.println("JobDetail JobDataMap = " + context.getJobDetail().getJobDataMap());
        System.out.println("Trigger JobDataMap = " + context.getTrigger().getJobDataMap());
        Integer jUserId = (Integer) context.getJobDetail().getJobDataMap().get("j_userId") + 1;
        context.getJobDetail().getJobDataMap().put("j_userId", jUserId);
        System.out.println("com.javartisan.quartz.HelloQuartzJob.execute is running...  " + uuid + "   jUserId  =  " + jUserId);
    }
}
