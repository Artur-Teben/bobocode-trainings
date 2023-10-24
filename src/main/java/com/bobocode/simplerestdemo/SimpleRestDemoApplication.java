package com.bobocode.simplerestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SimpleRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestDemoApplication.class, args);
    }
}
