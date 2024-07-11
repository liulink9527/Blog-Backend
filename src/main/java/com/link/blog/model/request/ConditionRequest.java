package com.link.blog.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author LiuWenqi
 * @Description 通用查询入参
 * @date 2024-06-30 20:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConditionRequest {

    /**
     * 页码
     */
    private Long current;

    /**
     * 条数
     */
    private Long size;

    /**
     * 搜索内容
     */
    private String keywords;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 标签id
     */
    private Integer tagId;

    /**
     * 相册id
     */
    private Integer albumId;

    /**
     * 登录类型
     */
    private Integer loginType;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 状态 1公开 2私密 3评论可见
     */
    private Integer status;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 是否审核
     */
    private Integer isReview;
}
