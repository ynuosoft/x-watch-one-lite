package com.xwatcher.core.watcher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;

import java.util.LinkedHashMap;

/**
 * Created by meng li on 2017/3/6.
 * 执行中的上下文,记录单个watcher单次任务的数据
 */
public class ExecutionContext_copy {

    private int watcherId;
    private String watcherName;
    private long threadId;
    private DateTime start;
    private DateTime end;
    private WatcherPayloadEntity payload;
    private WatcherConditionEntity condition;
    private WatcherActionEntity actions;

    /**
     * {ctx:{"xx":1,"xx":22}}
     */
    @JsonIgnore
    private JsonNode ctx;
    @JsonIgnore
    private LinkedHashMap<String,ObjectNode> ctx_l;

    public LinkedHashMap<String, ObjectNode> getCtx_l() {
        return ctx_l;
    }

    public void setCtx_l(LinkedHashMap<String, ObjectNode> ctx_l) {
        this.ctx_l = ctx_l;
    }

    public WatcherActionEntity getActions() {
        return actions;
    }

    public void setActions(WatcherActionEntity actions) {
        this.actions = actions;
    }

    public WatcherConditionEntity getCondition() {
        return condition;
    }

    public void setCondition(WatcherConditionEntity condition) {
        this.condition = condition;
    }

    public int getWatcherId() {
        return watcherId;
    }

    public void setWatcherId(int watcherId) {
        this.watcherId = watcherId;
    }

    public String getWatcherName() {
        return watcherName;
    }

    public void setWatcherName(String watcherName) {
        this.watcherName = watcherName;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
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

    public WatcherPayloadEntity getPayload() {
        return payload;
    }

    public void setPayload(WatcherPayloadEntity payload) {
        this.payload = payload;
    }

    public JsonNode getCtx() {
        return ctx;
    }

    public void setCtx(JsonNode ctx) {
        this.ctx = ctx;
    }
}
