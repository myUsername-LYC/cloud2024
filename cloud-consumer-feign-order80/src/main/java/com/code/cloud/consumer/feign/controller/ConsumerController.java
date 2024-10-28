package com.code.cloud.consumer.feign.controller;

import cn.hutool.core.date.DateTime;
import com.code.cloud.common.apis.PayFeignApi;
import com.code.cloud.common.entities.PayDTO;
import com.code.cloud.common.resp.ResultData;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@Tag(name = "消费微服务模块",description = "消费CRUD")
public class ConsumerController {

    @Autowired
    private PayFeignApi payFeignApi;


    @GetMapping(value = "con/get/info/{id}")
    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    public ResultData getInfo(@PathVariable("id") Integer id) {
        try {
            System.out.println("调用开始：" + DateTime.now());
            ResultData payInfo = payFeignApi.getPayInfo(id);
            System.out.println("调用结束：" + DateTime.now());
            return payInfo;
        } catch (Exception e) {
            System.out.println("调用异常：" + DateTime.now());
            e.printStackTrace();
            return ResultData.fail("9999",e.getMessage());
        }

    }

    @PostMapping(value = "con/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO){
        return payFeignApi.addPay(payDTO);
    }

    /**
     * 服务熔断例子
     *
     * @param id
     * @return
     */
    @GetMapping(value = "con/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public ResultData curdPay(@PathVariable("id") Integer id){
        if (id == -4) {
            throw new RuntimeException("----circuit id 不能-4");
        }
        return ResultData.success(payFeignApi.myCircuit(id));
    }

    /**
     * 舱壁隔离  ----SemaphoreBulkhead例子
     *
     * @param id
     * @return
     */
    @GetMapping(value = "con/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback",
            type = Bulkhead.Type.SEMAPHORE)
    public ResultData bulkhead4SemaphoreBulkhead(@PathVariable("id") Integer id){
        return ResultData.success(payFeignApi.myBulkhead(id));
    }

    @GetMapping(value = "con/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service",fallbackMethod = "myRatelimitFallback")
    public ResultData myRatelimit(@PathVariable("id") Integer id)
    {
        return ResultData.success(payFeignApi.myRatelimit(id));
    }
    public ResultData myRatelimitFallback(Integer id,Throwable t)
    {
        return ResultData.fail("9999","你被限流了，禁止访问/(ㄒoㄒ)/~~");
    }

    @GetMapping(value = "con/local/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service",fallbackMethod = "myRatelimitFallback")
    public ResultData myLocalRatelimit(@PathVariable("id") Integer id)
    {
        return ResultData.success("local ratelimit ....");
    }


    /**
     * 服务降级调用函数
     *
     * @param id
     * @param e
     * @return
     */
    private ResultData myCircuitFallback(Integer id, Throwable e) {
        return ResultData.fail("9999","系统繁忙！请稍后再试~");
    }

    /**
     * (船的)舱壁,隔离,THREADPOOL
     * @param id
     * @return
     */
    @GetMapping(value = "/con/pay/bulkhead2/{id}")
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadPoolFallback",type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadTHREADPOOL(@PathVariable("id") Integer id)
    {
        System.out.println(Thread.currentThread().getName()+"\t"+"enter the method!!!");
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName()+"\t"+"exist the method!!!");

        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + " Bulkhead.Type.THREADPOOL");
    }

    public CompletableFuture<String> myBulkheadPoolFallback(Integer id,Throwable t)
    {
        return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
    }
}
