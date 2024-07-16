package com.link.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.link.blog.constant.CommonConstant;
import com.link.blog.constant.RedisConstant;
import com.link.blog.dao.*;
import com.link.blog.entity.Tag;
import com.link.blog.entity.WebsiteConfig;
import com.link.blog.manager.ArticleManager;
import com.link.blog.manager.UniqueViewManager;
import com.link.blog.model.dto.*;
import com.link.blog.entity.Article;
import com.link.blog.entity.Message;
import com.link.blog.service.BlogInfoService;
import com.link.blog.service.RedisService;
import com.link.blog.service.UniqueViewService;
import com.link.blog.model.vo.BlogBackInfoVO;
import com.link.blog.util.IpUtils;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-23 14:05
 */
@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UniqueViewManager uniqueViewManager;

    @Autowired
    private ArticleManager articleManager;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private WebsiteConfigDao websiteConfigDao;

    @Autowired
    private TagDao tagDao;

    @Override
    public BlogBackInfoVO getBlogBackInfo() {
        // 查询博客访问量
        Object count = redisService.get(RedisConstant.BLOG_VIEWS_COUNT);
        Integer viewsCount = Integer.parseInt(Optional.ofNullable(count).orElse(0).toString());
        // 查询用户量
        Integer userCount = userInfoDao.selectCount(null).intValue();
        // 查询文章量
        Integer articleCount = articleDao.selectCount(new LambdaQueryWrapper<Article>().eq(Article::getIsDelete, CommonConstant.False)).intValue();
        // 查询留言量
        Integer messageCount = messageDao.selectCount(new LambdaQueryWrapper<Message>().eq(Message::getIsDelete, CommonConstant.False)).intValue();
        // 查询一周访问量
        List<UniqueViewDTO> oneWeekViews = uniqueViewManager.listOneWeekViews();
        // 查询文章贡献统计
        List<ArticleStatisticsDTO> articleStatisticsCount = articleManager.listArticleStatistics();
        // 查询分类数据
        List<CategoryDTO> categoryDTOList = categoryDao.listCategoryDTO();
        // 查询标签数据
        List<TagDTO> tagDTOList = BeanUtil.copyToList(tagDao.selectList(null), TagDTO.class);
        // 查询redis访问量前五的文章
        Map<Object, Double> articleViewsMap = redisService.zReverseRangeWithScores(RedisConstant.ARTICLE_VIEWS_COUNT, 0, 4);

        BlogBackInfoVO blogBackInfoVO = BlogBackInfoVO.builder()
                .viewsCount(viewsCount)
                .userCount(userCount)
                .messageCount(messageCount)
                .articleCount(articleCount)
                .categoryDTOList(categoryDTOList)
                .uniqueViewDTOList(oneWeekViews)
                .articleStatisticsDTOList(articleStatisticsCount)
                .tagDTOList(tagDTOList)
                .build();

        if (CollectionUtil.isNotEmpty(articleViewsMap)) {
            List<ArticleRankDTO> articleRankDTOList = convertList(articleViewsMap);
            blogBackInfoVO.setArticleRankDTOList(articleRankDTOList);
        }

        return blogBackInfoVO;
    }

    @Override
    public void report(HttpServletRequest request) {
        // 获取ip
        String ipAddress = IpUtils.getIpAddress(request);
        // 获取访问设备
        UserAgent userAgent = IpUtils.getUserAgent(request);
        Browser browser = userAgent.getBrowser();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        // 生成唯一用户标识
        String uuid = ipAddress + browser.getName() + operatingSystem.getName();
        String md5 = DigestUtils.md5DigestAsHex(uuid.getBytes());
        // 判断是否访问
        if (!redisService.sIsMember(RedisConstant.UNIQUE_VISITOR, md5)) {
            // 统计游客地域分布
            String ipSource = IpUtils.getIpSource(ipAddress);
            if (StringUtils.isNotBlank(ipSource)) {
                ipSource = ipSource.substring(0, 2)
                        .replace(CommonConstant.PROVICE, "")
                        .replace(CommonConstant.CITY, "");
                redisService.hIncr(RedisConstant.VISITOR_AREA, ipSource, 1L);
            } else {
                redisService.hIncr(RedisConstant.VISITOR_AREA, CommonConstant.UNKNOWN, 1L);
            }
            // 博客访问量+1
            redisService.incr(RedisConstant.BLOG_VIEWS_COUNT, 1L);
            // 保存当前访客标识
            redisService.sAdd(RedisConstant.UNIQUE_VISITOR, md5);
        }
    }

    @Override
    public WebsiteConfigDTO getWebsiteConfig() {
        WebsiteConfigDTO websiteConfigDTO;
        // 获取缓存数据
        Object websiteConfig = redisService.get(RedisConstant.WEBSITE_CONFIG);
        if (Objects.nonNull(websiteConfig)) {
            websiteConfigDTO = JSON.parseObject(websiteConfig.toString(), WebsiteConfigDTO.class);
        } else {
            // 从数据库中加载
            String config = websiteConfigDao.selectById(CommonConstant.DEFAULT_CONFIG_ID).getConfig();
            websiteConfigDTO = JSON.parseObject(config, WebsiteConfigDTO.class);
            redisService.set(RedisConstant.WEBSITE_CONFIG, config);
        }
        return websiteConfigDTO;
    }

    private List<ArticleRankDTO> convertList(Map<Object, Double> articleViewsMap) {

        // 保存文章id
        List<Integer> articleIdList = new ArrayList<>(articleViewsMap.size());
        articleViewsMap.forEach((k, v) -> articleIdList.add((Integer) k));

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Article::getId, Article::getArticleTitle)
                .in(Article::getId, articleIdList);

        List<Article> articles = articleDao.selectList(queryWrapper);
        return articles.stream()
                .map(article -> ArticleRankDTO.builder()
                        .articleTitle(article.getArticleTitle())
                        .viewsCount(articleViewsMap.get(article.getId()).intValue())
                        .build()
                )
                .sorted(Comparator.comparingInt(ArticleRankDTO::getViewsCount).reversed())
                .collect(Collectors.toList());


    }
}
