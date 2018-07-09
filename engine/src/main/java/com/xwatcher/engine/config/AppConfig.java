package com.xwatcher.engine.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.xwatcher.core.builder.WatcherLoader;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by meng li on 2017/2/17.
 */
@Configuration
@ComponentScan(basePackages = "com.xwatcher.*")
public class AppConfig {
    //    private Logger logger = LoggerFactory.getLogger(AppConfig.class);
    @Autowired
    private XWatcherProperties xWatcherProperties;

    @Autowired
    private WatcherLoader watcherLoader;

    /**
     * 注册Scheduling
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler poolTaskScheduler = new ThreadPoolTaskScheduler();
        poolTaskScheduler.setDaemon(false);
        poolTaskScheduler.setThreadGroupName("x-watcher-engine");
        poolTaskScheduler.setThreadNamePrefix("x-watcher-");
        poolTaskScheduler.initialize();
        return poolTaskScheduler;
    }
    /**
     * 注册watcher
     *
     * @return
     */
    @Bean
    public AppStartUp appStartUp() {
        watcherLoader.LoadWatcher(xWatcherProperties.getWatcherDir());
        AppStartUp appStartUp = new AppStartUp();
        return appStartUp;
    }
}
