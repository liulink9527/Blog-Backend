package com.link.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LiuWenqi
 * @Description 状态码枚举
 * @date 2024-06-15 19:12
 */
@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    /**
     * 成功
     */
    SUCCESS(20000, "成功"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR(50000, "系统异常"),
    /**
     * 操作失败
     */
    FAIL(51000, "操作失败"),
    /**
     * 非法参数错误
     */
    VALID_PARAMETER_ERROR(52000, "非法参数错误");


    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述
     */
    private String message;

}
