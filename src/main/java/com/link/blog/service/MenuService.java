package com.link.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.link.blog.entity.Menu;
import com.link.blog.model.vo.UserMenuVO;

import java.util.List;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-15 16:58
 */
public interface MenuService extends IService<Menu> {

    /**
     * 查看当前用户菜单
     */
    List<UserMenuVO> listUserMenus();
}
