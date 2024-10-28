package com.code.cloud.payment9001.controller;

import cn.hutool.core.util.IdUtil;
import com.code.cloud.payment9001.Props;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private Props props;

    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id)
    {
        return "Hello, myRatelimit欢迎到来 inputId:  "+id+" \t " + IdUtil.simpleUUID() + "port = " + port;
    }

    @GetMapping(value = "/pay/get/info")
    public String getInfo()
    {
        return "Hello, detail=" + props.getDetail() + ", password=" + props.getPassword();
    }
}
