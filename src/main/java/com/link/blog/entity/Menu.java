package com.link.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author LiuWenqi
 * @Description 菜单表
 * @date 2024-06-15 17:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_menu")
public class Menu {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 组件
     */
    private String component;
    /**
     * 菜单icon
     */
    private String icon;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 父id
     */
    private Integer parentId;
    /**
     * 是否隐藏 1-是 0-否
     */
    private Integer isHidden;


    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
