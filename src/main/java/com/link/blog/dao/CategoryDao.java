package com.link.blog.dao;

import com.link.blog.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.link.blog.model.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Link
 * @since 2024-06-25 07:47:56
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {

    List<CategoryDTO> listCategoryDTO();
}
