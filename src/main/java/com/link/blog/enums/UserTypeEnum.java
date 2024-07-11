package com.link.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LiuWenqi
 * @Description 用户类型枚举
 * @date 2024-06-30 20:55
 */
@AllArgsConstructor
@Getter
public enum UserTypeEnum {

    USER(1, "用户"),

    VISITOR(2, "游客")
    ;

    private final Integer type;

    private final String desc;

    /**
     * 获取用户类型
     * @param type
     * @return
     */
    public static UserTypeEnum getUserType(Integer type) {
        for (UserTypeEnum value : UserTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }

}
