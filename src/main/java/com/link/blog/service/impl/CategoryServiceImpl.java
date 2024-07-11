package com.link.blog.service.impl;

import com.link.blog.entity.Category;
import com.link.blog.dao.CategoryDao;
import com.link.blog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Link
 * @since 2024-06-25 07:47:56
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

}
