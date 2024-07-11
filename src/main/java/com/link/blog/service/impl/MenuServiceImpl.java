package com.link.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.link.blog.assembler.UserMenuAssembler;
import com.link.blog.dao.MenuDao;
import com.link.blog.entity.Menu;
import com.link.blog.service.MenuService;
import com.link.blog.model.vo.UserMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-15 17:00
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private UserMenuAssembler userMenuAssembler;

    @Override
    public List<UserMenuVO> listUserMenus() {

        //TODO 登录逻辑还没写，数据先写死

        // 获取当前用户可访问的菜单列表
        List<Menu> menuList = menuDao.listMenuByUserInfoId(1);
        // 获取一级目录
        List<Menu> firstMenuList = listParentMenu(menuList);
        // 获取二级目录
        Map<Integer, List<Menu>> childrenMenuMap = getChildrenMenuMap(menuList);

        return userMenuAssembler.convertUserMenuVO(firstMenuList, childrenMenuMap);
    }

    /**
     * 查看当前用户所有目录下的一级目录
     * @param menuList
     * @return
     */
    private List<Menu> listParentMenu(List<Menu> menuList) {
        return menuList.stream()
                .filter(menu -> Objects.isNull(menu.getParentId()))
                .sorted(Comparator.comparing(Menu::getOrderNum))
                .collect(Collectors.toList());
    }

    /**
     * 查询目录的子目录
     * @param menuList
     * @return
     */
    private Map<Integer, List<Menu>> getChildrenMenuMap(List<Menu> menuList) {
        return menuList.stream()
                .filter(menu -> Objects.nonNull(menu.getParentId()))
                .collect(Collectors.groupingBy(Menu::getParentId));
    }
}
