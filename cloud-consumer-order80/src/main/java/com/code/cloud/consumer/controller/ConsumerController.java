package com.code.cloud.consumer.controller;

import com.code.cloud.common.resp.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name = "消费微服务模块",description = "消费CRUD")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
//    public final static String url = "http://localhost:8001";
    public final static String service_name = "cloud-payment-service";
    public final static String url = "http://cloud-payment-service";

    @GetMapping(value = "con/discovery")
    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    public void getById() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }

        System.out.println("=========");
        for (String service : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            System.out.println("service_name: " + service);
            for (ServiceInstance instance : instances) {
                System.out.println("uri: " + instance.getUri());
                System.out.println("host: " + instance.getHost());
                System.out.println("port: " + instance.getPort());
            }
        }

    }

    @GetMapping(value = "con/get")
    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    public void get() {
        String url = "";
        List<ServiceInstance> list = discoveryClient.getInstances(service_name);
        System.out.println(list.size());
        for (ServiceInstance serviceInstance : list) {
            System.out.println(serviceInstance);
        }
    }

    @GetMapping(value = "con/get/info")
    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    public ResultData getInfo() {
        return ResultData.success(restTemplate.getForObject(url + "/refresh/get/info", ResultData.class));
    }
}
