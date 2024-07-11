package com.link.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author LiuWenqi
 * @Description 角色表
 * @since 2024-06-23 03:32:02
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_role")
public class Role {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleLabel;

    /**
     * 是否禁用
     */
    private Boolean isDisable;


    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
