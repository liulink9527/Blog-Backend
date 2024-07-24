package com.link.blog.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author LiuWenqi
 * @Description 修改文章置顶请求
 * @date 2024-07-21 15:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleTopRequest {

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    private Integer id;

    /**
     * 置顶状态
     */
    @NotNull(message = "置顶状态不能为空")
    private Integer isTop;
}
