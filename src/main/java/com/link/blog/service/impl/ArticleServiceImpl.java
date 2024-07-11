package com.link.blog.service.impl;

import com.link.blog.common.PageResult;
import com.link.blog.constant.RedisConstant;
import com.link.blog.entity.Article;
import com.link.blog.dao.ArticleDao;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.ArticleBackVO;
import com.link.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.link.blog.service.RedisService;
import com.link.blog.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Link
 * @since 2024-06-24 08:05:43
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private RedisService redisService;

    @Override
    public PageResult<ArticleBackVO> listArticleBack(ConditionRequest request) {
        // 查询文章总量
        Integer count = articleDao.countArticleBack(request);
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询后台文章
        List<ArticleBackVO> articleBackVOList = articleDao.listArticleBack(PageUtils.getLimitCurrent(), PageUtils.getSize(), request);
        // 查询文章点赞量和浏览量
        Map<Object, Double> viewsCountMap = redisService.zAllScore(RedisConstant.ARTICLE_VIEWS_COUNT);
        Map<String, Object> likesCountMap = redisService.hGetAll(RedisConstant.ARTICLE_LIKE_COUNT);
        // 封装点赞量和浏览量
        articleBackVOList.forEach(item -> {
            Double viewsCount = viewsCountMap.get(item.getId());
            if (Objects.nonNull(viewsCount)) {
                item.setViewsCount(viewsCount.intValue());
            }
            item.setLikeCount((Integer) likesCountMap.get(item.getId().toString()));
        });
        return new PageResult<>(articleBackVOList, count);
    }
}
