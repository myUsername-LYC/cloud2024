package com.code.cloud.common.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 表名：t_order
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO  implements Serializable {
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 金额
     */
    private Long money;

    /**
     * 订单状态: 0:创建中; 1:已完结
     */
    private Integer status;


}