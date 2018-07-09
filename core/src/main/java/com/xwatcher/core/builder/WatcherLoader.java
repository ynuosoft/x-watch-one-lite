package com.xwatcher.core.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by meng li on 2017/2/17.
 *
 */
@Scope("singleton")
@Component
public  class WatcherLoader {
    @Autowired
    private WatcherBuilder watcherBuilder;

    private static Logger logger = LoggerFactory.getLogger(WatcherLoader.class);
    /**
     * @param directory
     * @return
     */
    public  void LoadWatcher(String directory) {
        LoadWatcher(new File(directory));
    }
    /**
     * @param directory
     * @return
     */
    public void LoadWatcher(File directory) {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.exists()) {
              watcherBuilder.BuildWatcher(file);
            }
        }
    }
}
