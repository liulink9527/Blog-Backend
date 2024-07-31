package com.link.blog.dao;

import com.link.blog.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Link
 * @since 2024-07-19 10:40:08
 */
@Mapper
public interface ArticleTagDao extends BaseMapper<ArticleTag> {

    /**
     * 批量保存文章标签
     * @param id
     * @param tagsId
     */
    void saveArticleTags(@Param("id") Integer id,@Param("tagsId") List<Integer> tagsId);
}
