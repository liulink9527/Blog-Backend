package com.link.blog.service;

import com.link.blog.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.CategoryOptionVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Link
 * @since 2024-06-25 07:47:56
 */
public interface CategoryService extends IService<Category> {

    /**
     * 搜索栏文章分类选项
     * @param request
     * @return
     */
    List<CategoryOptionVO> listCategoryesOption(ConditionRequest request);
}
