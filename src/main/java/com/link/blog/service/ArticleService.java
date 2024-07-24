package com.link.blog.service;

import com.link.blog.common.PageResult;
import com.link.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.link.blog.model.dto.ArticleUploadDTO;
import com.link.blog.model.request.ArticleTopRequest;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.ArticleBackVO;
import io.minio.messages.DeleteRequest;

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
    void updateArticleDelete(DeleteRequest deleteRequest);
}
