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
    SUCCESS(200, "成功"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR(500, "系统异常"),
    /**
     * 操作失败
     */
    FAIL(501, "操作失败");



    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述
     */
    private String message;

}
