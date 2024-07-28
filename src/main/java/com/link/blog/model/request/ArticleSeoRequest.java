package com.link.blog.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author LiuWenqi
 * @Description 文章SEO请求
 * @date 2024-07-27 21:03
 */
@Data
public class ArticleSeoRequest {

    private List<Integer> articleIdList;
}
