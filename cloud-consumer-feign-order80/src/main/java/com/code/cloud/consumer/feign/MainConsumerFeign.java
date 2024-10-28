package com.code.cloud.consumer.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.code.cloud.common.apis")
@ComponentScan(basePackages = "com.code.cloud.common.config")
public class MainConsumerFeign {
    public static void main(String[] args) {
        SpringApplication.run(MainConsumerFeign.class, args);
    }
}