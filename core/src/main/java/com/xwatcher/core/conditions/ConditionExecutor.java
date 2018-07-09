package com.xwatcher.core.conditions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xwatcher.core.watcher.ExecutionContext;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;

/**
 * Created by meng li on 2017/2/27.
 */
public abstract class ConditionExecutor<T extends ConditionEntity> {

    @Autowired
    protected ObjectMapper objectMapper;

    private T entity;
    public T getEntity() {
        return entity;
    }
    private JsonNode jsonNode;
    public JsonNode getJsonNode() {
        return jsonNode;
    }
    public void setJsonNode(JsonNode jsonNode) throws IllegalArgumentException {
        this.jsonNode = jsonNode;
        entity =objectMapper.convertValue(jsonNode,convert2Entity());
    }
    protected abstract Class<T> convert2Entity();

    protected abstract boolean doCondition(ExecutionContext ctx);

    public boolean execute(ExecutionContext ctx) {
        //LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        //map.put("start_t", DateTime.now());
        boolean b = doCondition(ctx);
        //map.put("status", b);
        //map.put("end_t", DateTime.now());
        //ctx.getCtx_l().put("condition", map);

        ((ObjectNode) ctx.getCtx().with("ctx")).put("condition", b);
        return b;
    }
}
