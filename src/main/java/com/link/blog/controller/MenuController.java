package com.link.blog.controller;

import com.link.blog.common.Result;
import com.link.blog.service.MenuService;
import com.link.blog.model.vo.UserMenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-15 16:55
 */
@RestController
@Api(tags = "菜单模块")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "查看当前用户菜单")
    @GetMapping("/admin/user/menus")
    public Result<List<UserMenuVO>> listUserMenus() {
        return Result.ok(menuService.listUserMenus());
    }
}
