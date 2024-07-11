package com.link.blog.service.impl;

import com.link.blog.entity.Tag;
import com.link.blog.dao.TagDao;
import com.link.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Link
 * @since 2024-06-25 08:07:16
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagDao, Tag> implements TagService {

}
