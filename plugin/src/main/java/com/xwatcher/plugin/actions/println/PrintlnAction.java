package com.xwatcher.plugin.actions.println;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.xwatcher.core.actions.ActionExecutor;
import com.xwatcher.plugin.compents.mustache.MustacheHelper;
import com.xwatcher.core.watcher.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by meng li on 2017/2/27.
 */
@Scope("prototype")
@Qualifier("printlnAction")
@Component
public class PrintlnAction extends ActionExecutor<PrintlnActionEntity> {

    @Override
    protected Class<PrintlnActionEntity> convert2Entity() {
        return PrintlnActionEntity.class;
    }

    @Override
    public JsonNode doAction(ExecutionContext ctx) throws Exception {
        String printlnStr = MustacheHelper.getTemplateContent(this.getEntity().getTemplate(),
                objectMapper.convertValue(ctx.getCtx(), Map.class));
        System.out.println("ActionName:" + this.getActionName());
        System.out.println(printlnStr);

        return NullNode.instance;
    }
}
