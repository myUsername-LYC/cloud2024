package com.code.cloud.common.apis;

import com.code.cloud.common.resp.ResultData;
import com.code.cloud.common.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @auther zzyy
 * @create 2024-01-05 13:07
 */
@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi
{
    @Override
    public ResultData getPayByOrderNo(String orderNo)
    {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(),"对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
    }
}
