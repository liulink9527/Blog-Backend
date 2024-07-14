package com.link.blog.exception;

import com.link.blog.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LiuWenqi
 * @Description 业务异常
 * @date 2024-07-13 15:47
 */
@Getter
@AllArgsConstructor
public class BizException extends RuntimeException{

    /**
     * 错误码
     */
    private Integer code = StatusCodeEnum.FAIL.getCode();

    /**
     * 错误信息
     */
    private final String message;

    public BizException(String message) {
        this.message = message;
    }

    public BizException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getMessage();
    }

}
