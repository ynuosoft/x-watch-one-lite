package com.xwatcher.core.watcher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by meng li on 2017/3/6.
 * 执行中的上下文,记录单个watcher单次任务的数据
 */
public class ExecutionContext {
    private JsonNode ctx;
    public JsonNode getCtx() {
        return ctx;
    }
    public void setCtx(JsonNode ctx) {
        this.ctx = ctx;
    }
}
