package com.link.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.link.blog.common.PageResult;
import com.link.blog.constant.CommonConstant;
import com.link.blog.constant.RedisConstant;
import com.link.blog.context.UploadStrategyContext;
import com.link.blog.dao.ArticleTagDao;
import com.link.blog.dao.CategoryDao;
import com.link.blog.dao.TagDao;
import com.link.blog.entity.Article;
import com.link.blog.dao.ArticleDao;
import com.link.blog.entity.ArticleTag;
import com.link.blog.entity.Category;
import com.link.blog.entity.Tag;
import com.link.blog.enums.*;
import com.link.blog.exception.BizException;
import com.link.blog.model.dto.ArticleUploadDTO;
import com.link.blog.model.dto.FileAttachDTO;
import com.link.blog.model.dto.WebsiteConfigDTO;
import com.link.blog.model.request.*;
import com.link.blog.model.vo.ArticleBackVO;
import com.link.blog.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.link.blog.util.PageUtils;
import com.link.blog.util.StringUtils;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.data.MutableDataSet;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Link
 * @since 2024-06-24 08:05:43
 */
@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private RedisService redisService;

    @Autowired
    private BlogInfoService blogInfoService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ArticleTagDao articleTagDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;
    @Autowired
    private TagDao tagDao;

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

    @Transactional(rollbackFor = Exception.class)
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
                article.setArticleCover(webConfig.get().getArticleCover());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new BizException("设定文章封面失败");
            }
        }
        article.setUserId(1);
        this.saveOrUpdate(article);
        // 保存文章标签
        saveArticleTag(articleUploadDTO, article.getId());
    }

    @Override
    public void updateArticleTop(ArticleTopRequest articleTopRequest) {
        Article article = Article.builder().id(articleTopRequest.getId()).isTop(articleTopRequest.getIsTop()).build();
        articleDao.updateById(article);
    }

    @Override
    public void updateArticleDelete(ArticleDeleteRequest deleteRequest) {
        // 修改文章逻辑删除状态
        List<Article> articleList = deleteRequest.getIdList().stream()
                .map(id -> Article.builder()
                        .id(id)
                        .isTop(CommonConstant.False)
                        .isDelete(deleteRequest.getIsDelete())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(articleList);
    }

    @Override
    public void deleteArticles(List<Integer> articleIdList) {
        // 删除文章标签关联
        articleTagDao.delete(new LambdaQueryWrapper<ArticleTag>().in(ArticleTag::getArticleId, articleIdList));
        // 删除文章
        articleDao.deleteBatchIds(articleIdList);
    }

    @Override
    public void articleSeo(ArticleSeoRequest seoRequest) {

    }

    @Override
    public List<String> exportArticles(ArticleExportRequest exportRequest) {
        // 查询文章信息
        List<Article> articleList = articleDao.selectList(new LambdaQueryWrapper<Article>()
                .select(Article::getArticleTitle, Article::getArticleContent)
                .in(Article::getId, exportRequest.getArticleIdList()));
        // 写入文件并上传
        List<String> urlList = new ArrayList<>();
        for (Article article : articleList) {
            try (ByteArrayInputStream inputStream = new ByteArrayInputStream(article.getArticleContent().getBytes())) {
                String url = uploadStrategyContext.executeUploadFile(article.getArticleTitle() + FileExtEnum.MD.getExtName(), inputStream, FilePathEnum.MD.getPath());
                urlList.add(url);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new BizException("导出文章失败");
            }
        }
        return urlList;
    }

    @Override
    public void articleReptile(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements title = document.getElementsByClass("title-article");
            Elements tags = document.getElementsByClass("tag-link");
            Elements content = document.getElementsByClass("article_content");
            Assert.isTrue(StringUtils.isNotEmpty(content.toString()), BizCodeEnum.CRAWLING_ARTICLE_FAILED.getDesc());
            // HTML内容转为Markdown格式
            String newContent = content.get(0).toString().replaceAll("<code>", "<code class=\"lang-java\">");
            MutableDataSet options = new MutableDataSet();
            String markdown = FlexmarkHtmlConverter.builder(options).build().convert(newContent).replace("lang-java", "java");

            // 获取随机图片
            String response = restTemplate.getForObject(CommonConstant.IMG_API_URL, String.class);
            log.info("获取随机图片成功 res:{}", response);
            Object imgUrl = JSON.parseObject(response).get("imgurl");

            Article articleReptile = Article.builder().userId(1)
                    .articleContent(markdown)
                    .categoryId(CommonConstant.OTHER_CATEGORY_ID)
                    .type(FileTypeEnum.REPRINT.getType())
                    .originalUrl(url)
                    .articleTitle(title.get(0).text())
                    .articleCover(imgUrl.toString())
                    .build();
            articleDao.insert(articleReptile);

            // 为文章添加标签
            List<Integer> tagsId = new ArrayList<>();
            tags.forEach(item -> {
                String tag = item.text();
                // 数据库是否已存在该标签
                Tag tagData = tagDao.selectOne(new LambdaQueryWrapper<Tag>().eq(Tag::getTagName, tag));
                if (Objects.isNull(tagData)) {
                    tagData = Tag.builder().tagName(tag).build();
                    tagDao.insert(tagData);
                }
                tagsId.add(tagData.getId());
            });
            articleTagDao.saveArticleTags(articleReptile.getId(), tagsId);

            log.info("文章抓取成功, 内容为:{}", JSON.toJSONString(articleReptile));
        } catch (IOException e) {
            log.error("堆栈:{}", e);
            throw new BizException("文章抓取失败");
        }
    }

    /**
     * 保存文章标签
     *
     * @param articleUploadDTO
     * @param articleId
     */
    private void saveArticleTag(ArticleUploadDTO articleUploadDTO, Integer articleId) {
        // 编辑文章则删除文章所有标签
        if (Objects.nonNull(articleUploadDTO.getId())) {
            articleTagDao.delete(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, articleUploadDTO.getId()));
        }
        // 添加文章标签
        List<String> tagNameList = articleUploadDTO.getTagNameList();
        if (CollectionUtil.isNotEmpty(tagNameList)) {
            // 查询已存在的标签
            List<Tag> existTagList = tagService.list(new LambdaQueryWrapper<Tag>().in(Tag::getTagName, tagNameList));
            List<String> existTagNameList = existTagList.stream().map(Tag::getTagName).collect(Collectors.toList());
            List<Integer> existTagIdList = existTagList.stream().map(Tag::getId).collect(Collectors.toList());
            // 对比新增的不存在的标签
            tagNameList.removeAll(existTagNameList);
            if (CollectionUtil.isNotEmpty(tagNameList)) {
                List<Tag> tagList = tagNameList.stream().map(item -> Tag.builder().tagName(item).build()).collect(Collectors.toList());
                tagService.saveBatch(tagList);
                List<Integer> tagIdList = tagList.stream().map(Tag::getId).collect(Collectors.toList());
                existTagIdList.addAll(tagIdList);
            }
            // 提取标签id绑定文章
            List<ArticleTag> articleTagList = existTagIdList.stream().map(item -> ArticleTag.builder()
                            .articleId(articleId)
                            .tagId(item)
                            .build())
                    .collect(Collectors.toList());
            articleTagService.saveBatch(articleTagList);
        }
    }

    /**
     * 保存文章分类名称
     */
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
