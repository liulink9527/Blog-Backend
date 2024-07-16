package com.link.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author LiuWenqi
 * @Description 上传文章DTO
 * @date 2024-07-13 20:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleUploadDTO {

    /**
     * id
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章封面
     */
    private String articleCover;

    /**
     * 文章分类名
     */
    private String categoryName;

    /**
     * 文章标签列表
     */
    private List<String> tagNameList;

    /**
     * 附件
     */
    private List<FileAttachDTO> fileAttach;

    /**
     * 文章类型
     */
    private Integer type;

    /**
     * 原文链接
     */
    private String originalUrl;

    /**
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 状态
     */
    private Integer status;


}
