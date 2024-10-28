package com.code.cloud.consumer.feign.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置openfeign开启重试
 *
 */
@Configuration
public class RetryConfig {
    @Bean
    public Retryer retryer() {
        // 重试初始间隔时间100ms， 重试最大间隔时间1s，最大重试次数3
//        return new Retryer.Default(100,1, 3);

        // 默认
        return Retryer.NEVER_RETRY;
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
