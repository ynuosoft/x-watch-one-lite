package com.xwatcher.plugin.conditions.never;

import com.fasterxml.jackson.databind.JsonNode;
import com.xwatcher.core.conditions.ConditionExecutor;
import com.xwatcher.core.watcher.ExecutionContext;

/**
 * Created by meng li on 2017/3/3.
 */
public class NeverCondition extends ConditionExecutor<NeverConditionEntity> {
    @Override
    protected Class<NeverConditionEntity> convert2Entity() {
        return NeverConditionEntity.class;
    }

    protected boolean doCondition(ExecutionContext executionContext) {
        return false;

    }
}
