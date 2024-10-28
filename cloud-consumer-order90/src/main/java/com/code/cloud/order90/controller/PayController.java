package com.code.cloud.order90.controller;

import com.code.cloud.common.apis.PayFeignApi;
import com.code.cloud.common.resp.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {
    @Autowired
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/pay/test/{id}")
    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    public ResultData test(@PathVariable("id") Integer id) {
        return ResultData.success(payFeignApi.myRatelimit(id));
    }
}
