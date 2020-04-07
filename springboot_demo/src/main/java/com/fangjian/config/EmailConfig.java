package com.fangjian.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "fangjian.email")
@Data
public class EmailConfig {
    private String subject;
    private String from;
    private String to;
    private String[] cc;
}
