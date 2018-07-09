package com.xwatcher.core.triggers.cron;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xwatcher.core.stats.history.WatcherHistory;
import com.xwatcher.core.triggers.TriggerExecutor;
import com.xwatcher.core.watcher.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;


/**
 * Created by meng li on 2017/2/27.
 */

@Component
@Scope("prototype")
@Qualifier("cronTrigger")
public class CronTrigger extends TriggerExecutor<CronTriggerEntity> {

    private Logger logger = LoggerFactory.getLogger(CronTrigger.class);

    @Autowired
    private WatcherHistory watcherHistory;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    protected Class<CronTriggerEntity> convert2Entity() {
        return CronTriggerEntity.class;
    }

    @Override
    public void doTrigger() {

        threadPoolTaskScheduler.schedule(() -> {
            runable();
        }, new org.springframework.scheduling.support.CronTrigger(this.getEntity().getCronExpression()));
        //threadPoolTaskScheduler.getScheduledThreadPoolExecutor().
    }

    /**
     *
     */
    void runable()
    {
        ExecutionContext ctx = new ExecutionContext();
        //
//        Map<String,Object> ctx_p=new HashMap<>();
//        Map<String,Object> ctx_l=new HashMap<>();
//        ctx_l.put("w_id",watcherId);
//        ctx_l.put("w_name",watcherName);
//        ctx_l.put("start_t", DateTime.now());
//        ctx_l.put("t_id",Thread.currentThread().getId());
//        ctx_l.put("t_name",Thread.currentThread().getName());
//        ctx_l.put("metadata",metadata);
//
//        ctx_p.put("ctx",ctx_l);
//        ctx.setCtx_l(ctx_p);

//        设置ctx上下文jsonnode
        JsonNode ctx_o=objectMapper.createObjectNode();
        ((ObjectNode)ctx_o).set("ctx",objectMapper.createObjectNode().set("metadata",this.getMetadata()));
        ((ObjectNode)ctx_o.with("ctx")).put("w_id",this.getWatcherId());
        ((ObjectNode)ctx_o.with("ctx")).put("w_name",this.getWatcherName());
        //((ObjectNode)ctx_o.with("ctx")).set("start_t",((ObjectNode)new Date()));
        ((ObjectNode)ctx_o.with("ctx")).put("t_id",Thread.currentThread().getId());
        ((ObjectNode)ctx_o.with("ctx")).put("t_name",Thread.currentThread().getName());
        ctx.setCtx(ctx_o);

        this.getInputs().forEach(e->e.execute(ctx));
        //
        if (this.getCondition().execute(ctx)) {
            //
            this.getActions().forEach(a -> a.execute(ctx));
        }
        // ctx_l.put("end_t", DateTime.now());
        //((ObjectNode)ctx_o.with("ctx")).put("end_t", DateTime.now());
        //JsonNode jsonNode=(JsonNode)ctx_l.get("");
        watcherHistory.doHistory(ctx);
    }
}
