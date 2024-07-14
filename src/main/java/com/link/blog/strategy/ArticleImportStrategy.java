package com.link.blog.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author LiuWenqi
 * @Description 文章导入策略
 * @date 2024-07-13 20:31
 */
public interface ArticleImportStrategy {

    /**
     * 导入文章
     * @param file
     */
    void importArticle(MultipartFile file);
}
