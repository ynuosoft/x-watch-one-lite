package com.xwatcher.core.stats.history;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwatcher.core.triggers.cron.CronTrigger;
import com.xwatcher.core.watcher.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by meng li on 2017/3/10.
 */
@Component
@Scope("singleton")
public class WatcherHistory {

    private Logger logger = LoggerFactory.getLogger(WatcherHistory.class);

    @Autowired
    private ObjectMapper objectMapper;
    /**
     * @param ctx
     */
    public void doHistory(ExecutionContext ctx) {

        try {
            logger.info(objectMapper.writeValueAsString(ctx));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
