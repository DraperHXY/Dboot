package com.draper.dboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author draper_hxy
 */
@Slf4j
@SpringBootApplication
public class DbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbootApplication.class, args);
        log.info("系统启动成功");
    }
}
