package com.xwatcher.plugin.inputs.simple;

import com.xwatcher.core.inputs.InputEntity;

import java.util.Map;

/**
 * Created by meng li on 2017/2/27.
 */
public class SimpleInputEntity extends InputEntity {

    private Map<String,Object> params;
    public Map<String, Object> getParams() {
        return params;
    }
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
