package com.link.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author LiuWenqi
 * @Description
 * @Date 2024/7/31 星期三 下午2:37
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

    ORIGINAL(1, "原创"),

    REPRINT(2, "转载"),

    TRANSLATE(3, "翻译")

    ;


    private Integer type;

    private String desc;
}
