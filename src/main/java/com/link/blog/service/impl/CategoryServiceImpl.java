package com.link.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.link.blog.entity.Category;
import com.link.blog.dao.CategoryDao;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.CategoryOptionVO;
import com.link.blog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.link.blog.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Link
 * @since 2024-06-25 07:47:56
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<CategoryOptionVO> listCategoryesOption(ConditionRequest request) {
        // 搜索分类
        List<Category> categoryList = categoryDao.selectList(new LambdaQueryWrapper<Category>()
                .like(StringUtils.isNotEmpty(request.getKeywords()), Category::getCategoryName, request.getKeywords())
                .orderByDesc(Category::getId));
        return BeanUtil.copyToList(categoryList, CategoryOptionVO.class);
    }
}
