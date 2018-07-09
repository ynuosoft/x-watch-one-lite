package com.xwatcher.core.watcher;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by meng li on 2017/3/9.
 */
public class WatcherConditionEntity {
    private DateTime start;
    private DateTime end;

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }
}
