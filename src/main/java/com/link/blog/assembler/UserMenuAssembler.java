package com.link.blog.assembler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.link.blog.constant.CommonConstant;
import com.link.blog.entity.Menu;
import com.link.blog.model.vo.UserMenuVO;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-15 20:27
 */
@Component
public class UserMenuAssembler {

    public List<UserMenuVO> convertUserMenuVO(List<Menu> firstMenuList,  Map<Integer, List<Menu>> childrenMenuMap) {

        List<UserMenuVO> res = new ArrayList<>();

        for (Menu menu : firstMenuList) {
            UserMenuVO userMenuVO = new UserMenuVO();
            List<UserMenuVO> children = new ArrayList<>();
            // 当前菜单的子菜单
            List<Menu> childrenMenuList = childrenMenuMap.get(menu.getId());
            if (CollectionUtil.isNotEmpty(childrenMenuList)) {
                userMenuVO = BeanUtil.copyProperties(menu, UserMenuVO.class);
                children = childrenMenuList.stream()
                        .sorted(Comparator.comparing(Menu::getOrderNum))
                        .map(childrenMenu -> {
                            UserMenuVO vo = BeanUtil.copyProperties(childrenMenu, UserMenuVO.class);
                            vo.setHidden(childrenMenu.getIsHidden().equals(CommonConstant.TRUE));
                            return vo;
                        }).collect(Collectors.toList());
            } else {
                userMenuVO.setPath(menu.getPath());
                userMenuVO.setComponent(CommonConstant.LAYOUT_COMPONENT);
                children.add(UserMenuVO.builder()
                        .name(menu.getName())
                        .path("")
                        .icon(menu.getIcon())
                        .component(menu.getComponent())
                        .build());
            }
            userMenuVO.setHidden(menu.getIsHidden().equals(CommonConstant.TRUE));
            userMenuVO.setChildren(children);

            res.add(userMenuVO);
        }

        return res;
    }
}
