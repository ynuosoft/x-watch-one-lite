package com.xwatcher.plugin.conditions.always;

import com.fasterxml.jackson.databind.JsonNode;
import com.xwatcher.core.conditions.ConditionExecutor;
import com.xwatcher.core.watcher.ExecutionContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by meng li on 2017/2/27.
 */
@Scope("prototype")
@Qualifier("alwaysCondition")
@Component
public class AlwaysCondition extends ConditionExecutor<AlwaysConditionEntity> {
    @Override
    protected Class<AlwaysConditionEntity> convert2Entity() {
        return AlwaysConditionEntity.class;
    }

    @Override
    protected boolean doCondition(ExecutionContext executionContext) {
        return true;
    }
}
