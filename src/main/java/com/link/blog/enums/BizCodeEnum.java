package com.link.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LiuWenqi
 * @Description 业务码枚举
 * @date 2024-06-15 19:12
 */
@Getter
@AllArgsConstructor
public enum BizCodeEnum {

    CRAWLING_ARTICLE_FAILED(10001, "抓取文章失败"),

    ;


    private Integer code;

    private String desc;
}
