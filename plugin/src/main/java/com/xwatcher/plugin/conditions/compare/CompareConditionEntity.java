package com.xwatcher.plugin.conditions.compare;

import com.xwatcher.core.conditions.ConditionEntity;

import java.util.Map;

/**
 * Created by meng li on 2017/2/27.
 */
public class CompareConditionEntity extends ConditionEntity {

    /**
     * or
     * and
     */
    private String quantifier="or";
    public String getQuantifier() {
        return quantifier;
    }
    public void setQuantifier(String quantifier) {
        this.quantifier = quantifier;
    }
    private Map<String,Map<String,Object>> params;
    public Map<String, Map<String, Object>> getParams() {
        return params;
    }

    public void setParams(Map<String, Map<String, Object>> params) {
        this.params = params;
    }
}
