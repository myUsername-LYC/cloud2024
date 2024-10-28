package com.code.cloud.seata.storage.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.db.sql.Order;
import com.code.cloud.common.entities.OrderDTO;
import com.code.cloud.common.resp.ResultData;
import com.code.cloud.seata.storage.service.StorageService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @auther zzyy
 * @create 2023-12-01 18:09
 */

@RestController
public class StorageController
{
    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public ResultData decrease(Long productId, Integer count) {

        storageService.decrease(productId, count);
        return ResultData.success("扣减库存成功!");
    }


//    /**
//     * 扣减库存
//     */
//    @RequestMapping(value = "/storage/decrease1")
//    ResultData decrease1(@RequestBody OrderDTO order){
//        {
//            System.out.println("storage中扣减库存开始 order=" + order);
//            storageService.decrease1(order);
//            return ResultData.success("扣减库存和余额成功!");
//        }
//    }
//
    /**
     * 扣减库存
     */
    @RequestMapping(value = "/storage/decrease1")
    public ResultData decrease1(@RequestBody OrderDTO order){
        {
            System.out.println("storage中扣减库存开始 order=" + order);
            storageService.decrease1(order);
            return ResultData.success("扣减库存和余额成功!");
        }
    }
}
