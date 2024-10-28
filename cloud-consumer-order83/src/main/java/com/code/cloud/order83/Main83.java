package com.code.cloud.order83;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.code.cloud.common.apis"})
@ComponentScan(basePackages = {"com.code.cloud.order83", "com.code.cloud.common"})
public class Main83 {
    public static void main(String[] args) {
        SpringApplication.run(Main83.class);
    }
}