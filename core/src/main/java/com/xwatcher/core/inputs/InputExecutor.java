package com.xwatcher.core.inputs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xwatcher.core.watcher.ExecutionContext;
import com.xwatcher.core.watcher.WatcherPayloadEntity;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by meng li on 2017/2/27.
 */
public abstract class InputExecutor<T extends InputEntity> {


    @Autowired
    protected ObjectMapper objectMapper;

    private String inputName;
    private T entity;

    public T getEntity() {
        return entity;
    }

    private JsonNode jsonNode;

    public JsonNode getJsonNode() {
        return jsonNode;
    }

    public void setJsonNode(String inputName, JsonNode jsonNode) {
        this.jsonNode = jsonNode;
        this.inputName = inputName;
        this.entity = objectMapper.convertValue(jsonNode, convert2Entity());
    }
    protected abstract Class<T> convert2Entity();
    protected abstract JsonNode doInput(ExecutionContext ctx);

    public void execute(ExecutionContext ctx) {
        JsonNode input = doInput(ctx);
        //ctx.getCtx_l().put("payload", payload_01);
        ((ObjectNode) ctx.getCtx().with("ctx")).set("input",
                objectMapper.createObjectNode().set(inputName, input));
    }
}
