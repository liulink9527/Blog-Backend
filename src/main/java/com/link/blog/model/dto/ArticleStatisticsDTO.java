package com.link.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenqi
 * @Description 文章贡献数量DTO
 * @date 2024-06-24 21:18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleStatisticsDTO {

    /**
     * 日期
     */
    private String date;

    /**
     * 文章贡献数量
     */
    private Integer count;
}
