package com.xwatcher.plugin.conditions.arraycompare;

import com.xwatcher.core.conditions.ConditionEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by meng li on 2017/3/6.
 */
public class ArrayCompareConditionEntity extends ConditionEntity {

    /**
     * all
     * some
     */
    private String quantifier = "some";

    public String getQuantifier() {
        return quantifier;
    }

    public void setQuantifier(String quantifier) {
        this.quantifier = quantifier;
    }

    private Map<String, Map<String, Map<String, Object>>> params;

    public Map<String, Map<String, Map<String, Object>>> getParams() {
        return params;
    }
    public void setParams(Map<String, Map<String, Map<String, Object>>> params) {
        this.params = params;
    }
}
