package com.link.blog.model.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author LiuWenqi
 * @Description 搜索栏标签选项VO
 * @date 2024-07-20 20:24
 */
@Data
@Builder
public class TagOptionVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 分类名
     */
    private String tagName;
}
