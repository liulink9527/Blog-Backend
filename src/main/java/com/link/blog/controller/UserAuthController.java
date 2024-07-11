package com.link.blog.controller;

import com.link.blog.common.Result;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.UserAreaVO;
import com.link.blog.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-30 20:33
 */
@Api(tags = "用户账户模块")
@RestController
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @ApiOperation(value = "获取用户区域分布")
    @GetMapping("/admin/users/area")
    public Result<List<UserAreaVO>> getUserArea(ConditionRequest request) {
        return Result.ok(userAuthService.getUserArea(request));
    }
}
