package com.javartisan.quartz.simple;

import com.javartisan.quartz.HelloQuartzJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Hello world!
 */
public class QuartzSimpleScheduleStarter {
    public static void main(String[] args) throws Exception {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        // 1：创建调度器
        Scheduler scheduler = schedulerFactory.getScheduler();
        //2： 启动调度器
        scheduler.start();

        //3： 配置Job
        JobDetail jobDetail = JobBuilder.newJob(HelloQuartzJob.class).
                withIdentity("HelloQuartzJob", "HelloQuartzJobGroup").usingJobData("j_userId", 100).usingJobData("j_username", "daxin").build();


        //4：配置触发器
        TriggerBuilder triggerBuilder = TriggerBuilder.newTrigger().withIdentity("HelloQuartzJobTrigger", "HelloQuartzJobTriggerGroup").startNow()
                //withMisfireHandlingInstructionFireNow设置触发错过的策略
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(2).withMisfireHandlingInstructionFireNow()).usingJobData("t_userId", "001")
                .usingJobData("t_username", "daxin");

        // 作业在当前时间的五小时之后开始调度执行，startAt是开始调度的规则
        // triggerBuilder.startAt(DateBuilder.nextGivenSecondDate(new Date(), 3600 * 5));

        Trigger trigger = triggerBuilder.build();
        ;
        //5：调度Job
        scheduler.scheduleJob(jobDetail, trigger);

        // 听见监听器
        SimpleJobListener simpleJobListener = new SimpleJobListener();
        scheduler.getListenerManager().addJobListener(simpleJobListener, KeyMatcher.keyEquals(new JobKey("HelloQuartzJob", "HelloQuartzJobGroup")));
        SimpleTriggerListener simpleTriggerListener = new SimpleTriggerListener();
        scheduler.getListenerManager().addTriggerListener(simpleTriggerListener, KeyMatcher.keyEquals(new TriggerKey("HelloQuartzJobTrigger", "HelloQuartzJobTriggerGroup")));

        // 移除监听器
        scheduler.getListenerManager().removeJobListener(simpleTriggerListener.getName());
        TimeUnit.SECONDS.sleep(60);
        //6：停止调度器
        scheduler.shutdown();


        System.out.println("org.quartz.Scheduler.shutdown() is running ... ");
    }


}
