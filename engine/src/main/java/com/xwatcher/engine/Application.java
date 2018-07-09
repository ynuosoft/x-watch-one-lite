package com.xwatcher.engine;



import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Created by meng li on 2017/2/16.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setRegisterShutdownHook(false);
        springApplication.setWebEnvironment(false);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);

    }
}
