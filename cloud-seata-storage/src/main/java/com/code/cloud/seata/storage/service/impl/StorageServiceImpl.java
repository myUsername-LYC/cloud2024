package com.code.cloud.seata.storage.service.impl;

import com.code.cloud.common.apis.AccountFeignApi;
import com.code.cloud.common.entities.OrderDTO;
import com.code.cloud.seata.storage.mapper.StorageMapper;
import com.code.cloud.seata.storage.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @auther zzyy
 * @create 2023-12-01 18:08
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService
{

    @Resource
    private StorageMapper storageMapper;
    @Resource
    private AccountFeignApi accountFeignApi;

    /**
     * 扣减库存
     */
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");
        storageMapper.decrease(productId,count);
        log.info("------->storage-service中扣减库存结束");
    }

    @Override
    public void decrease1(OrderDTO orderDTO) {
        this.decrease(orderDTO.getProductId(), orderDTO.getCount());
        System.out.println();
        log.info("-------> 订单微服务开始调用Account账号，做扣减money");
        accountFeignApi.decrease(orderDTO.getUserId(), orderDTO.getMoney());
        log.info("-------> 订单微服务结束调用Account账号，做扣减完成");
    }
}