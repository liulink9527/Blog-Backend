package com.link.blog.model.vo;

import com.link.blog.model.dto.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author LiuWenqi
 * @Description 博客后台数据VO
 * @date 2024-06-23 14:08
 */
@Data
@Builder
public class BlogBackInfoVO {

    /**
     * 访问量
     */
    private Integer viewsCount;

    /**
     * 留言量
     */
    private Integer messageCount;

    /**
     * 用户量
     */
    private Integer userCount;

    /**
     * 文章量
     */
    private Integer articleCount;

    /**
     * 文章分类数据
     */
    private List<CategoryDTO> categoryDTOList;

    /**
     * 文章标签数据
     */
    private List<TagDTO> tagDTOList;

    /**
     * 文章贡献统计数据
     */
    private List<ArticleStatisticsDTO> articleStatisticsDTOList;

    /**
     * 一周访问量
     */
    private List<UniqueViewDTO> uniqueViewDTOList;

    /**
     * 文章排行
     */
    private List<ArticleRankDTO> articleRankDTOList;
}
