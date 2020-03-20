package com.fangjian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync//@EnableAsync 注解开启异步处理
public class SpringBootBusinessApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBusinessApplication.class, args);
    }
}
