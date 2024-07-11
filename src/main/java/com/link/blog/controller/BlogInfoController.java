package com.link.blog.controller;

import com.link.blog.common.Result;
import com.link.blog.service.BlogInfoService;
import com.link.blog.model.vo.BlogBackInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-20 19:48
 */
@RestController
@Api(value = "博客信息模块")
public class BlogInfoController {

    @Autowired
    private BlogInfoService blogInfoService;

    @ApiOperation(value = "获取后台首页数据")
    @GetMapping("/admin")
    public Result<BlogBackInfoVO> getBlogBackInfo() {
        return Result.ok(blogInfoService.getBlogBackInfo());
    }


    @ApiOperation(value = "访客记录")
    @PostMapping("/report")
    public Result report(HttpServletRequest request) {
        blogInfoService.report(request);
        return Result.ok();
    }

}
