package com.xwatcher.core.triggers.cron;

import com.xwatcher.core.triggers.TriggerEntity;

/**
 * Created by meng li on 2017/2/16.
 */
public class CronTriggerEntity extends TriggerEntity {

    private String cronExpression;
    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

}
