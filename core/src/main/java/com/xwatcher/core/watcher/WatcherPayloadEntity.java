package com.xwatcher.core.watcher;

import com.fasterxml.jackson.databind.JsonNode;
import org.joda.time.DateTime;

/**
 * Created by meng li on 2017/3/9.
 * payload
 */
public class WatcherPayloadEntity {
    private JsonNode payload;
    private DateTime start;
    private DateTime end;

    public JsonNode getPayload() {
        return payload;
    }

    public void setPayload(JsonNode payload) {
        this.payload = payload;
    }

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
