package com.link.blog.handler;

import com.link.blog.common.Result;
import com.link.blog.enums.StatusCodeEnum;
import com.link.blog.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author LiuWenqi
 * @Description 全局异常处理
 * @date 2024-07-13 15:59
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理服务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public Result errorHandler(BizException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result errorHandler(MethodArgumentNotValidException e) {
        return Result.fail(StatusCodeEnum.VALID_PARAMETER_ERROR.getCode(), Objects.requireNonNull(e.getBindingResult().getFieldError().getDefaultMessage()));
    }

    /**
     * 处理系统异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(StatusCodeEnum.SYSTEM_ERROR);
    }


}
