package com.xwatcher.core.actions;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xwatcher.core.triggers.cron.CronTrigger;
import com.xwatcher.core.watcher.ExecutionContext;
import com.xwatcher.core.watcher.WatcherActionEntity;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.LinkedHashMap;


/**
 * Created by meng li on 2017/2/27.
 */
public abstract class ActionExecutor<T extends ActionEntity> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ObjectMapper objectMapper;

    private String actionName;

    public String getActionName() {
        return actionName;
    }

    private T entity;

    public T getEntity() {
        return entity;
    }

    private JsonNode jsonNode;

    public JsonNode getJsonNode() {
        return jsonNode;
    }

    /**
     * @param actionName
     * @param jsonNode
     */
    public final void setJsonNode(String actionName, JsonNode jsonNode) {
        this.jsonNode = jsonNode;
        this.actionName = actionName;
        this.entity = objectMapper.convertValue(jsonNode, convert2Entity());
    }

    protected abstract Class<T> convert2Entity();

    protected abstract JsonNode doAction(ExecutionContext ctx) throws Exception;

    public void execute(ExecutionContext ctx) {
        try {
            JsonNode action = doAction(ctx);
            ((ObjectNode) ctx.getCtx().with("ctx")).set("action",
                    objectMapper.createObjectNode().set(this.actionName, action));

        } catch (Exception e) {
            logger.error("action execute error ", e);
        } finally {
        }
    }
}
