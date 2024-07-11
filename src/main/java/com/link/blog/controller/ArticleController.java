package com.link.blog.controller;

import com.link.blog.common.PageResult;
import com.link.blog.common.Result;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.ArticleBackVO;
import com.link.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-07-08 21:54
 */
@RestController
@Api(value = "文章模块")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "查看后台文章")
    @GetMapping("/admin/articles")
    public Result<PageResult<ArticleBackVO>> listArticlesBack(ConditionRequest request) {
        return Result.ok(articleService.listArticleBack(request));
    }
}
