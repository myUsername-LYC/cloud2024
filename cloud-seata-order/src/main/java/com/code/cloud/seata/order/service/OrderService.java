package com.code.cloud.seata.order.service;

import com.code.cloud.seata.order.entites.Order;

/**
 * @auther zzyy
 * @create 2023-12-01 17:52
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}