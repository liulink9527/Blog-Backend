package com.link.blog.model.vo;

import com.link.blog.model.dto.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author LiuWenqi
 * @Description 后台文章VO
 * @date 2024-07-09 20:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleBackVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 文章封面
     */
    private String articleCover;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 发表时间
     */
    private LocalDateTime createTime;

    /**
     * 点赞量
     */
    private Integer likeCount;

    /**
     * 浏览量
     */
    private Integer viewsCount;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 文章标签名
     */
    private List<TagDTO> tagDTOList;

    /**
     * 文章类型
     */
    private Integer type;

    /**
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 文章状态 1公开 2私密 3评论可见
     */
    private Integer status;
}
