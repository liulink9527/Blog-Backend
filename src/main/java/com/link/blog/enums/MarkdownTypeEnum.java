package com.link.blog.enums;

import com.link.blog.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LiuWenqi
 * @Description Markdown文章类型枚举
 * @date 2024-07-13 20:22
 */
@Getter
@AllArgsConstructor
public enum MarkdownTypeEnum {

    /**
     * 普通文章
     */
    NORMAL("", "normalArticleImportStrategyImpl"),

    /**
     * hexo文章
     */
    HEXO("hexo", "hexoArticleImportStrategyImpl");

    private final String type;

    private final String strategy;

    public static String getMarkdownType(String name) {
        if (StringUtils.isEmpty(name)) {
            return NORMAL.getStrategy();
        }
        for (MarkdownTypeEnum value : MarkdownTypeEnum.values()) {
            if (value.getType().equalsIgnoreCase(name)) {
                return value.getStrategy();
            }
        }
        return null;
    }
}
