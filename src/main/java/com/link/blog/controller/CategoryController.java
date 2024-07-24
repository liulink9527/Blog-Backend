package com.link.blog.controller;

import com.link.blog.common.Result;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.CategoryOptionVO;
import com.link.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-07-20 19:57
 */
@RestController
@Api(value = "分类模块")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "搜索栏文章分类选项")
    @GetMapping("/admin/categories/search")
    public Result<List<CategoryOptionVO>> listCategoryesOption(ConditionRequest request) {
        return Result.ok(categoryService.listCategoryesOption(request));
    }
}
