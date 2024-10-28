package com.code.cloud.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @auther zzyy
 * @create 2023-05-26 18:40
 */
@Service
public class FlowLimitService
{
    @SentinelResource(value = "common")
    public String common()
    {
        System.out.println("------FlowLimitService come in");
        return "------FlowLimitService common";
    }

    public String handlerException(BlockException ex)
    {
        System.out.println("handlerException -- 1 服务降级异常处理");
        return "handlerException -- 1 服务降级异常处理";
    }
}