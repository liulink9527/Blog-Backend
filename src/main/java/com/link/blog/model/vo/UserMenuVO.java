package com.link.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author LiuWenqi
 * @Description 用户菜单VO
 * @date 2024-06-15 19:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserMenuVO {
    /**
     * 菜单名
     */
    private String name;
    /**
     * 路径
     */
    private String path;
    /**
     * 组件
     */
    private String component;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否隐藏
     */
    private Boolean Hidden;
    /**
     * 子菜单
     */
    private List<UserMenuVO> children;
}
