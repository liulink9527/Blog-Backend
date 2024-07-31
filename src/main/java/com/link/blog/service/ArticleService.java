package com.link.blog.service;

import com.link.blog.common.PageResult;
import com.link.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.link.blog.model.dto.ArticleUploadDTO;
import com.link.blog.model.request.*;
import com.link.blog.model.vo.ArticleBackVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Link
 * @since 2024-06-24 08:05:43
 */
public interface ArticleService extends IService<Article> {

    /**
     * 查询后台文章
     * @param request
     * @return
     */
    PageResult<ArticleBackVO> listArticleBack(ConditionRequest request);

    /**
     * 添加或修改文章
     * @param articleUploadDTO
     */
    void saveOrUpdateArticle(ArticleUploadDTO articleUploadDTO);

    /**
     * 修改文章置顶
     * @param articleTopRequest
     */
    void updateArticleTop(ArticleTopRequest articleTopRequest);

    /**
     * 恢复或删除文章
     * @param deleteRequest
     */
    void updateArticleDelete(ArticleDeleteRequest deleteRequest);

    /**
     * 物理删除文章
     * @param articleIdList
     */
    void deleteArticles(List<Integer> articleIdList);

    /**
     * 文章SEO
     * @param seoRequest
     */
    void articleSeo(ArticleSeoRequest seoRequest);

    /**
     * 批量导出文章
     * @return
     */
    List<String> exportArticles(ArticleExportRequest exportRequest);

    /**
     * 爬取文章
     * @param url
     */
    void articleReptile(String url);
}
