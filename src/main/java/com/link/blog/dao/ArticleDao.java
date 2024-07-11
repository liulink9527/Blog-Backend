package com.link.blog.dao;

import com.link.blog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.link.blog.model.dto.ArticleStatisticsDTO;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.ArticleBackVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Link
 * @since 2024-06-24 08:05:43
 */
@Mapper
public interface ArticleDao extends BaseMapper<Article> {

    /**
     * 查询文章统计数据
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<ArticleStatisticsDTO> listArticleStatistics(Date startTime, Date endTime);

    /**
     * 查询后台文章总量
     *
     * @param request
     * @return
     */
    Integer countArticleBack(@Param("condition") ConditionRequest request);

    /**
     * 查询后台文章
     * @param current
     * @param size
     * @param request
     * @return
     */
    List<ArticleBackVO> listArticleBack(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionRequest request);
}
