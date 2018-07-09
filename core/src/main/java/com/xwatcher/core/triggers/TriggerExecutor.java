package com.xwatcher.core.triggers;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.xwatcher.core.actions.ActionExecutor;
import com.xwatcher.core.conditions.ConditionExecutor;
import com.xwatcher.core.inputs.InputExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by meng li on 2017/2/27.
 */
public abstract class TriggerExecutor<T extends TriggerEntity>{


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
    public final void  setJsonNode(JsonNode jsonNode) throws IllegalArgumentException {
        this.jsonNode = jsonNode;
        entity=objectMapper.convertValue(jsonNode, convert2Entity());
    }
    protected abstract  Class<T> convert2Entity();

    private String watcherId;
    private String watcherName;
    private JsonNode metadata;
    private List<InputExecutor> inputs;
    private ConditionExecutor condition;
    private List<ActionExecutor> actions;

    public String getWatcherId() {
        return watcherId;
    }

    public void setWatcherId(String watcherId) {
        this.watcherId = watcherId;
    }

    public String getWatcherName() {
        return watcherName;
    }

    public void setWatcherName(String watcherName) {
        this.watcherName = watcherName;
    }

    public JsonNode getMetadata() {
        return metadata;
    }

    public void setMetadata(JsonNode metadata) {
        this.metadata = metadata;
    }

    public List<InputExecutor> getInputs() {
        return inputs;
    }

    public void setInputs(List<InputExecutor> inputs) {
        this.inputs = inputs;
    }

    public ConditionExecutor getCondition() {
        return condition;
    }

    public void setCondition(ConditionExecutor condition) {
        this.condition = condition;
    }

    public List<ActionExecutor> getActions() {
        return actions;
    }

    public void setActions(List<ActionExecutor> actions) {
        this.actions = actions;
    }

    public abstract void doTrigger();
}
