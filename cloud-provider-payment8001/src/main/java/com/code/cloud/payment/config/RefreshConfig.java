package com.code.cloud.payment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "test")
@Data
@Component
public class RefreshConfig {
    private String content;
}
