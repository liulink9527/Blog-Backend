package com.link.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author LiuWenqi
 * @Description 用户区域VO
 * @date 2024-06-30 20:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAreaVO implements Serializable {

    /**
     * 地区名
     */
    private String name;

    /**
     * 数量
     */
    private Long value;
}
