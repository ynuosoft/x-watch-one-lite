package com.xwatcher.engine.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by meng li on 2017/2/24.
 */
@Component
@ConfigurationProperties(prefix = "xwatcher.config")
public class XWatcherProperties {
    /**
     * 配置文件路径
     */
    private String watcherDir;

    public String getWatcherDir() {
        return watcherDir;
    }

    public void setWatcherDir(String watcherDir) {
        this.watcherDir = watcherDir;
    }
}
