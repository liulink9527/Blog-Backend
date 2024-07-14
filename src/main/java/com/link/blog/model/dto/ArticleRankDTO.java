package com.link.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LiuWenqi
 * @Description 文章排名DTO
 * @date 2024-06-25 21:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRankDTO {

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 访问量
     */
    private Integer viewsCount;
}
