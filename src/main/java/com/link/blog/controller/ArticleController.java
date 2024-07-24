package com.link.blog.controller;

import com.link.blog.annotation.OptLog;
import com.link.blog.common.PageResult;
import com.link.blog.common.Result;
import com.link.blog.constant.OptTypeConstant;
import com.link.blog.context.ArticleImportStrategyContext;
import com.link.blog.model.request.ArticleTopRequest;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.ArticleBackVO;
import com.link.blog.service.ArticleService;
import io.minio.messages.DeleteRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


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

    @Autowired
    private ArticleImportStrategyContext articleImportStrategyContext;

    @ApiOperation(value = "查看后台文章")
    @GetMapping("/admin/articles")
    public Result<PageResult<ArticleBackVO>> listArticlesBack(ConditionRequest request) {
        return Result.ok(articleService.listArticleBack(request));
    }

    @ApiOperation(value = "批量导入文章")
    @PostMapping("/admin/articles/import")
    public Result importArticles(MultipartFile file, @RequestParam(required = false) String type) {
        articleImportStrategyContext.importArticles(file, type);
        return Result.ok();
    }

    @OptLog(optType = OptTypeConstant.UPDATE)
    @ApiOperation(value = "修改文章置顶")
    @PutMapping("/admin/articles/top")
    public Result updateArticleTop(@Valid @RequestBody ArticleTopRequest articleTopRequest) {
        articleService.updateArticleTop(articleTopRequest);
        return Result.ok();
    }

    @ApiOperation(value = "恢复或删除文章")
    @PutMapping("/admin/articles")
    public Result updateArticleDelete(DeleteRequest deleteRequest) {
        articleService.updateArticleDelete(deleteRequest);
        return Result.ok();
    }

}
