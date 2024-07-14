package com.link.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-07-13 21:05
 */
@Getter
@AllArgsConstructor
public enum ArticleStatusEnum {

    /**
     * 公开
     */
    PUBLIC(1, "公开"),

    /**
     * 私密
     */
    SECRET(2, "私密"),

    /**
     * 草稿
     */
    DRAFT(3, "草稿")

    ;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;


}
