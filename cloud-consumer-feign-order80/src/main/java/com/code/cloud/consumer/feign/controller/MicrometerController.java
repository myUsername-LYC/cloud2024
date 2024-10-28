package com.code.cloud.consumer.feign.controller;

import com.code.cloud.common.apis.PayFeignApi;
import com.code.cloud.common.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicrometerController {

    @Autowired
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/con/micrometer/{id}")
    public ResultData myMicrometer(@PathVariable("id") Integer id)
    {
        return ResultData.success(payFeignApi.myMicrometer(id));
    }
}
