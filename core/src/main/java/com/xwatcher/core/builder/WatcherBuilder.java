package com.xwatcher.core.builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwatcher.core.actions.ActionExecutor;
import com.xwatcher.core.conditions.ConditionExecutor;
import com.xwatcher.core.utils.MD5Helper;
import com.xwatcher.core.utils.PluginSuffix;
import com.xwatcher.core.inputs.InputExecutor;
import com.xwatcher.core.triggers.TriggerExecutor;
import com.xwatcher.core.watcher.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by meng li on 2017/2/17.
 */
@Component
@Scope("singleton")
public  class WatcherBuilder {

    private Logger logger = LoggerFactory.getLogger(WatcherBuilder.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ApplicationContext applicationContext;
    /**
     * @param file
     */
    public void BuildWatcher(File file) {
        try {
            JsonNode rootNode = objectMapper.readTree(file);
            BuildWatcher(rootNode);
        } catch (Exception e) {
            logger.error("build watcher error ", e);
        }
    }
    /**
     * @param json
     */
    public void BuildWatcher(String json) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            BuildWatcher(rootNode);
        } catch (IOException e) {
            logger.error("build watcher error ", e);
        } finally {
        }
    }
    /**
     * @param rootNode
     */
    public void BuildWatcher(JsonNode rootNode) {
        if (rootNode.isArray()) {
            Iterator<JsonNode> iterator = rootNode.iterator();
            while (iterator.hasNext()) {
                BuildWatcher01(iterator.next());
            }
        } else {
            BuildWatcher01(rootNode);
        }
    }
    /**
     * @param rootNode
     */
    void BuildWatcher01(JsonNode rootNode) {
        Watcher watcher = applicationContext.getBean(Watcher.class);

        watcher.setWatcherId(MD5Helper.Md5(rootNode.toString()));
        watcher.setWatcherName(rootNode.get("watcherName").textValue());
        //原始数据
        watcher.setMetadata(rootNode.get("metadata"));
        watcher.setTrigger(BuildTrigger(rootNode.get("trigger")));
        watcher.setInputs(BuildInputs(rootNode.get("input")));
        watcher.setCondition(BuildCondition(rootNode.get("condition")));
        watcher.setActions(BuildActions(rootNode.get("action")));
        //启动
        watcher.StartWatcher();
    }
    /**
     * 转换trigger
     *
     * @param jsonNode
     * @return
     */
    TriggerExecutor BuildTrigger(JsonNode jsonNode) {
        Map.Entry<String, JsonNode> entry = jsonNode.fields().next();
        TriggerExecutor executor = applicationContext.getBean(entry.getKey() + PluginSuffix.TriggerStr, TriggerExecutor.class);
        executor.setJsonNode(entry.getValue());
        return executor;
    }
//
//    /**
//     * @param jsonNode
//     * @return
//     */
//    InputExecutor BuildInput(JsonNode jsonNode) {
//        Map.Entry<String, JsonNode> entry = jsonNode.fields().next();
//        InputExecutor executor = applicationContext.getBean(entry.getKey() + PluginSuffix.InputStr, InputExecutor.class);
//        executor.setJsonNode(entry.getValue());
//        return executor;
//    }
    /**
     * @param jsonNode
     * @return
     */
    List<InputExecutor> BuildInputs(JsonNode jsonNode) {
        List<InputExecutor> list = new LinkedList<InputExecutor>();
        Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
        while (it.hasNext()) {
            Map.Entry<String, JsonNode> entryInputName = it.next();
            Map.Entry<String, JsonNode> entryInputDefine = entryInputName.getValue().fields().next();
            {
                InputExecutor executor = applicationContext.getBean(entryInputDefine.getKey() + PluginSuffix.InputStr, InputExecutor.class);
                executor.setJsonNode(entryInputName.getKey(), entryInputDefine.getValue());
                list.add(executor);
            }
        }
        return list;
    }

    /**
     * @param jsonNode
     * @return
     */
    ConditionExecutor BuildCondition(JsonNode jsonNode) {
        Map.Entry<String, JsonNode> entry = jsonNode.fields().next();
        ConditionExecutor executor = applicationContext.getBean(entry.getKey() + PluginSuffix.ConditionStr, ConditionExecutor.class);
        executor.setJsonNode(entry.getValue());
        return executor;
    }

    /**
     * @param jsonNode
     * @return
     */
    List<ActionExecutor> BuildActions(JsonNode jsonNode) {
        List<ActionExecutor> list = new LinkedList<ActionExecutor>();
        Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
        while (it.hasNext()) {

            Map.Entry<String, JsonNode> entryActionName = it.next();
            Map.Entry<String, JsonNode> entryActionDefine = entryActionName.getValue().fields().next();
            {
                ActionExecutor executor = applicationContext.getBean(entryActionDefine.getKey() + PluginSuffix.ActionStr, ActionExecutor.class);
                executor.setJsonNode(entryActionName.getKey(), entryActionDefine.getValue());
                list.add(executor);
            }
        }
        return list;
    }
}