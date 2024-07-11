package com.link.blog.service;

import com.link.blog.common.PageResult;
import com.link.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.ArticleBackVO;

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
}
