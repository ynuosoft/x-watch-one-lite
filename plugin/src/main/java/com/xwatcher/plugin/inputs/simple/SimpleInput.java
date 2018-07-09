package com.xwatcher.plugin.inputs.simple;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwatcher.core.inputs.InputExecutor;
import com.xwatcher.core.watcher.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by meng li on 2017/2/27.
 */
@Scope("prototype")
@Qualifier("simpleInput")
@Component
public class SimpleInput extends InputExecutor<SimpleInputEntity> {

    private Logger logger = LoggerFactory.getLogger(SimpleInput.class);

    @Override
    protected Class<SimpleInputEntity> convert2Entity() {
        return SimpleInputEntity.class;
    }

    @Override
    public JsonNode doInput(ExecutionContext executionContext) {
        return getJsonNode();
    }
}
