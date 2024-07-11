package com.link.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-25 19:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    /**
     * id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 该分类下文章数量
     */
    private Integer articleCount;
}
