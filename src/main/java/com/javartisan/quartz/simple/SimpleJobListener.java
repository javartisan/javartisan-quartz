package com.javartisan.quartz.simple;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class SimpleJobListener implements JobListener {

    @Override
    public String getName() {
        return "SimpleJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("com.javartisan.quartz.simple.SimpleJobListener.jobToBeExecuted");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("com.javartisan.quartz.simple.SimpleJobListener.jobExecutionVetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("com.javartisan.quartz.simple.SimpleJobListener.jobWasExecuted");
    }
}
