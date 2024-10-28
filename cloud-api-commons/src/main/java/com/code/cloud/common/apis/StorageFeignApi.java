package com.code.cloud.common.apis;

import com.code.cloud.common.entities.OrderDTO;
import com.code.cloud.common.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @auther zzyy
 * @create 2024-01-06 14:06
 */
@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi
{
    /**
     * 扣减库存
     */
    @PostMapping(value = "/storage/decrease")
    ResultData decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

    /**
     * 扣减库存
     */
    @PostMapping(value = "/storage/decrease1", consumes = "application/json")
    ResultData decrease1(@RequestBody OrderDTO dto);
}
