package com.xwatcher.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by meng li on 2017/2/27.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
//        springApplication.setRegisterShutdownHook(false);
//        springApplication.setWebEnvironment(false);
        springApplication.run(args);

    }
}
