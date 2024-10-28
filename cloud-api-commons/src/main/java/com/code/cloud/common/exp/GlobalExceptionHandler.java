package com.code.cloud.common.exp;

import com.code.cloud.common.resp.ResultData;
import com.code.cloud.common.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @auther zzyy
 * @create 2023-12-22 17:09
 */
@Slf4j
//@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> runtimeException(Exception e)
    {
        System.out.println("#####come in GlobalExceptionHandler");
        log.error("全局异常信息:{}",e.getMessage(),e);

        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e)
    {
        System.out.println("#####come in GlobalExceptionHandler");
        log.error("全局异常信息:{}",e.getMessage(),e);

        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }
}
