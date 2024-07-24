package com.link.blog.service;

import com.link.blog.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.TagOptionVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Link
 * @since 2024-06-25 08:07:16
 */
public interface TagService extends IService<Tag> {

    /**
     * 搜索栏文章标签选项
     * @param request
     * @return
     */
    List<TagOptionVO> listTagsOption(ConditionRequest request);
}
