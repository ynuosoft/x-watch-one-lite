package com.xwatcher.plugin.conditions.contain;

import com.xwatcher.core.conditions.ConditionEntity;

import java.util.HashSet;
import java.util.Map;

/**
 * Created by meng li on 2017/3/7.
 */
public class ContainConditionEntity extends ConditionEntity {

    private Map<String,HashSet<Object>> params;

    public Map<String, HashSet<Object>> getParams() {
        return params;
    }

    public void setParams(Map<String, HashSet<Object>> params) {
        this.params = params;
    }
}
