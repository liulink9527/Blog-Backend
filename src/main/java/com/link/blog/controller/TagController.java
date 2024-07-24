package com.link.blog.controller;

import com.link.blog.common.Result;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.TagOptionVO;
import com.link.blog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-07-20 20:21
 */
@RestController
@Api(value = "标签模块")
public class TagController {

    @Autowired
    private TagService tagService;


    @ApiOperation(value = "搜索栏文章标签选项")
    @GetMapping("/admin/tags/search")
    public Result<List<TagOptionVO>> listTagsOption(ConditionRequest request) {
        return Result.ok(tagService.listTagsOption(request));
    }
}
