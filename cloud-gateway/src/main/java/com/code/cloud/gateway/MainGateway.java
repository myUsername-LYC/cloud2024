package com.code.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MainGateway {
    public static void main(String[] args) {
        SpringApplication.run(MainGateway.class, args);
    }
}