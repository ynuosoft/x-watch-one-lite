package com.xwatcher.core.watcher;

import com.fasterxml.jackson.databind.JsonNode;
import com.xwatcher.core.actions.ActionExecutor;
import com.xwatcher.core.conditions.ConditionExecutor;
import com.xwatcher.core.inputs.InputExecutor;
import com.xwatcher.core.triggers.TriggerExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by meng li on 2017/3/6.
 */
@Component
@Scope("prototype")
public class Watcher {

    private String watcherId;
    private String watcherName;
    private JsonNode metadata;
    private TriggerExecutor trigger;
    private List<InputExecutor> inputs;
    private ConditionExecutor condition;
    private List<ActionExecutor> actions;

    public void StartWatcher() {

        trigger.setWatcherId(watcherId);
        trigger.setWatcherName(watcherName);
        trigger.setMetadata(metadata);
        trigger.setInputs(inputs);
        trigger.setCondition(condition);
        trigger.setActions(actions);

        /**
         *
         */
        trigger.doTrigger();
    }

    public void setWatcherId(String watcherId) {
        this.watcherId = watcherId;
    }

    public void setWatcherName(String watcherName) {
        this.watcherName = watcherName;
    }

    public void setMetadata(JsonNode metadata) {
        this.metadata = metadata;
    }

    public void setTrigger(TriggerExecutor trigger) {
        this.trigger = trigger;
    }

    public void setInputs(List<InputExecutor> inputs) {
        this.inputs = inputs;
    }

    public void setCondition(ConditionExecutor condition) {
        this.condition = condition;
    }

    public void setActions(List<ActionExecutor> actions) {
        this.actions = actions;
    }
}
