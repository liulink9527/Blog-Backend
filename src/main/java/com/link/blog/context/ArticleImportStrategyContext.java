package com.link.blog.context;

import com.link.blog.enums.ArticleStatusEnum;
import com.link.blog.enums.MarkdownTypeEnum;
import com.link.blog.strategy.ArticleImportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author LiuWenqi
 * @Description 文章导入策略上下文
 * @date 2024-07-20 13:37
 */
@Service
public class ArticleImportStrategyContext {

    @Autowired
    private Map<String, ArticleImportStrategy> articleImportStrategyMap;

    public void importArticles(MultipartFile file, String type) {
        articleImportStrategyMap.get(MarkdownTypeEnum.getMarkdownType(type)).importArticle(file);
    }
}
