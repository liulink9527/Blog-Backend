package com.link.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-25 20:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagDTO {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 标签名
     */
    private String tagName;
}
