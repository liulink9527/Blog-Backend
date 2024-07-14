package com.link.blog.common;

import com.link.blog.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author LiuWenqi
 * @Description 统一返回结果
 * @date 2024-06-15 17:57
 */
@Data
@AllArgsConstructor
public class Result<T> {
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public static <T> Result<T> ok() {
        return new Result<>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMessage(), null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> ok(T data, String message) {
        return new Result<>(StatusCodeEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> Result<T> fail() {
        return new Result<>(StatusCodeEnum.FAIL.getCode(), StatusCodeEnum.FAIL.getMessage(), null);
    }

    public static <T> Result<T> fail(StatusCodeEnum statusCodeEnum) {
        return new Result<>(statusCodeEnum.getCode(), statusCodeEnum.getMessage(), null);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(StatusCodeEnum.FAIL.getCode(), StatusCodeEnum.FAIL.getMessage(), data);
    }

    public static <T> Result<T> fail(T data, String message) {
        return new Result<>(StatusCodeEnum.FAIL.getCode(), message, data);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}
