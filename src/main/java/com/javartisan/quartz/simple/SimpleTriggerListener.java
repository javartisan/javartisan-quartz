package com.javartisan.quartz.simple;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

public class SimpleTriggerListener implements TriggerListener {

    @Override
    public String getName() {
        return "SimpleTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {

        System.out.println("com.javartisan.quartz.simple.SimpleTriggerListener.triggerFired");
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("com.javartisan.quartz.simple.SimpleTriggerListener.triggerMisfired");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        System.out.println("com.javartisan.quartz.simple.SimpleTriggerListener.triggerComplete");
    }
}
