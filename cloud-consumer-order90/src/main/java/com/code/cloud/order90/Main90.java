package com.code.cloud.order90;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.code.cloud.common.apis")
public class Main90 {
    public static void main(String[] args) {
        SpringApplication.run(Main90.class, args);
    }
}