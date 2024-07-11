package com.link.blog.common;

import com.link.blog.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author LiuWenqi
 * @Description
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
}
