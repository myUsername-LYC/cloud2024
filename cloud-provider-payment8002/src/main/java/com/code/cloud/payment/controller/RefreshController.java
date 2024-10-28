package com.code.cloud.payment.controller;

import com.code.cloud.common.resp.ResultData;
import com.code.cloud.common.resp.ReturnCodeEnum;
import com.code.cloud.payment.config.RefreshConfig;
import com.code.cloud.payment.dto.PayDTO;
import com.code.cloud.payment.entity.Pay;
import com.code.cloud.payment.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @auther zzyy
 * @create 2023-12-21 17:34
 */
@RestController
@Slf4j
@Tag(name = "刷新配置测试",description = "刷新配置测试")
@RefreshScope
public class RefreshController
{

    @Value("${atguigu.info}")
    private String info;
    @Autowired
    private RefreshConfig config;
    @GetMapping(value = "/refresh/get/info")
    public ResultData getInfoByConsul()
    {
        System.out.println("info: " + info);
        System.out.println("config: " + config);
        return ResultData.success("info: " + info + "\t" + "config: " + config);
    }


}
