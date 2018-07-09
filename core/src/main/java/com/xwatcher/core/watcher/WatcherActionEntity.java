package com.xwatcher.core.watcher;

import com.fasterxml.jackson.databind.JsonNode;
import org.joda.time.DateTime;

/**
 * Created by meng li on 2017/3/9.
 */
public class WatcherActionEntity {
    private DateTime start;
    private DateTime end;
    private JsonNode result;

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

    public JsonNode getResult() {
        return result;
    }

    public void setResult(JsonNode result) {
        this.result = result;
    }
}
