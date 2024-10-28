package com.code.cloud.seata.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.code.cloud.seata.order.mapper")
@EnableFeignClients(basePackages = "com.code.cloud.common.apis")
public class Main2001 {
    public static void main(String[] args) {
        SpringApplication.run(Main2001.class);
    }
}