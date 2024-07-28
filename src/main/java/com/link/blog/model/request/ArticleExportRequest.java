package com.link.blog.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author LiuWenqi
 * @Description 文章导出请求
 * @date 2024-07-27 21:52
 */
@Data
public class ArticleExportRequest {

    private List<Integer> articleIdList;
}
