package com.xwatcher.plugin.conditions.contain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwatcher.core.conditions.ConditionExecutor;
import com.xwatcher.core.watcher.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;

/**
 * Created by meng li on 2017/3/7.
 */
@Scope("prototype")
@Qualifier("containCondition")
@Component
public class ContainCondition extends ConditionExecutor<ContainConditionEntity> {
    private Logger logger = LoggerFactory.getLogger(ContainCondition.class);
    @Override
    protected Class<ContainConditionEntity> convert2Entity() {
        return  ContainConditionEntity.class;
    }
    /**
     * @param executionContext
     * @return
     */
    protected boolean doCondition(ExecutionContext executionContext) {
        boolean b0 = true;
        try {
            for (Map.Entry<String, HashSet<Object>> entry_01 : this.getEntity().getParams().entrySet()) {
                Object ctx_propertyValue = getValue(entry_01.getKey(), executionContext.getCtx());
                b0 = b0 && entry_01.getValue().contains(ctx_propertyValue);
                if (!b0) {
                    break;
                }
            }
        } catch (Exception e) {
            b0 = false;
            logger.error("contain doCndition error", e);
        }
        return b0;
    }
    /**
     * 从json本体中获取指定的值
     * @param ctx_propertyValue
     * @return
     */
    Object getValue(String ctx_propertyValue, JsonNode ctx) {
        JsonNode idNode = null;
//        小数点需要转义符号，否则认为是正则表达式\\
        String[] propertyNameArr = ctx_propertyValue.split("\\.");
        for (String propertyName : propertyNameArr) {
            if (idNode == null) {
                idNode = ctx.path(propertyName);
            } else {
                idNode = idNode.path(propertyName);
            }
            if (idNode.isMissingNode()) {
                throw new IllegalArgumentException("could not find " + ctx_propertyValue);
            }
        }
        if (idNode.isInt()) {
            return idNode.asInt();
        }
        return idNode.textValue();
    }
}
