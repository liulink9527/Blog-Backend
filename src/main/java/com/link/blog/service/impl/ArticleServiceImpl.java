package com.link.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONArray;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.xiaoymin.knife4j.core.util.CommonUtils;
import com.link.blog.common.PageResult;
import com.link.blog.constant.RedisConstant;
import com.link.blog.dao.CategoryDao;
import com.link.blog.entity.Article;
import com.link.blog.dao.ArticleDao;
import com.link.blog.entity.Category;
import com.link.blog.enums.ArticleStatusEnum;
import com.link.blog.exception.BizException;
import com.link.blog.model.dto.ArticleUploadDTO;
import com.link.blog.model.dto.FileAttachDTO;
import com.link.blog.model.dto.WebsiteConfigDTO;
import com.link.blog.model.request.ConditionRequest;
import com.link.blog.model.vo.ArticleBackVO;
import com.link.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.link.blog.service.BlogInfoService;
import com.link.blog.service.RedisService;
import com.link.blog.util.PageUtils;
import com.link.blog.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    @Autowired
    private BlogInfoService blogInfoService;

    @Autowired
    private CategoryDao categoryDao;

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

    @Override
    public void saveOrUpdateArticle(ArticleUploadDTO articleUploadDTO) {
        // 查询博客配置信息
        CompletableFuture<WebsiteConfigDTO> webConfig = CompletableFuture.supplyAsync(() -> blogInfoService.getWebsiteConfig());

        // 保存文章分类
        Category category = saveArticleCategory(articleUploadDTO);
        // 保存或修改文章
        Article article = BeanUtil.copyProperties(articleUploadDTO, Article.class);
        if (Objects.nonNull(category)) {
            article.setCategoryId(category.getId());
        }
        if (Objects.nonNull(articleUploadDTO.getFileAttach())) {
            List<FileAttachDTO> fileAttachDTOList = articleUploadDTO.getFileAttach();
            article.setFileAttach(JSON.toJSONString(fileAttachDTOList));
        }
        // 设定默认文章封面
        if (StringUtils.isEmpty(article.getArticleCover())) {
            try {
//                article.setArticleCover(webConfig.get().getArticleCover() + "&time=" + CommonUtils.);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new BizException("设定文章封面失败");
            }
        }


    }

    private Category saveArticleCategory(ArticleUploadDTO articleUploadDTO) {
        // 判断分类是否存在
        Category category = categoryDao.selectOne(new LambdaQueryWrapper<Category>().eq(Category::getCategoryName, articleUploadDTO.getCategoryName()));
        if (Objects.isNull(category) && !articleUploadDTO.getStatus().equals(ArticleStatusEnum.DRAFT.getStatus())) {
            category = Category.builder().categoryName(articleUploadDTO.getCategoryName()).build();
            categoryDao.insert(category);
        }
        return category;
    }

}
