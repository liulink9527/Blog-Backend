package com.link.blog.model.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author LiuWenqi
 * @Description 搜索栏分类选项VO
 * @date 2024-07-20 20:02
 */
@Data
@Builder
public class CategoryOptionVO {

    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名
     */
    private String categoryName;

}
