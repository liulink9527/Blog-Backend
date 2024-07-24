package com.link.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.link.blog.entity.Tag;
import com.link.blog.dao.TagDao;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.TagOptionVO;
import com.link.blog.service.TagService;
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
 * @since 2024-06-25 08:07:16
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagDao, Tag> implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public List<TagOptionVO> listTagsOption(ConditionRequest request) {
        List<Tag> tagList = tagDao.selectList(new LambdaQueryWrapper<Tag>()
                .like(StringUtils.isNotEmpty(request.getKeywords()), Tag::getTagName, request.getKeywords())
                .orderByDesc(Tag::getId));
        return BeanUtil.copyToList(tagList, TagOptionVO.class);
    }
}
