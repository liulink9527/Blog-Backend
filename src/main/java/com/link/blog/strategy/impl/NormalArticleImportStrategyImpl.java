package com.link.blog.strategy.impl;

import com.alibaba.fastjson2.JSON;
import com.link.blog.enums.ArticleStatusEnum;
import com.link.blog.exception.BizException;
import com.link.blog.model.dto.ArticleUploadDTO;
import com.link.blog.service.ArticleService;
import com.link.blog.strategy.ArticleImportStrategy;
import com.link.blog.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-07-13 20:32
 */
@Slf4j
@Service("normalArticleImportStrategyImpl")
public class NormalArticleImportStrategyImpl implements ArticleImportStrategy {

    @Autowired
    private ArticleService articleService;

    @Override
    public void importArticle(MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new BizException(StringUtils.format("上传文件为空 file:{}", JSON.toJSONString(file)));
        }
        // 获取文件名作为文章标题
        String articleTitle = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        // 获取文章内容
        StringBuilder articleContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            while (reader.ready()) {
                articleContent.append((char) reader.read());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BizException("导入文章失败");
        }
        // 保存文章
        ArticleUploadDTO articleUploadDTO = ArticleUploadDTO.builder()
                .articleTitle(articleTitle)
                .articleContent(articleContent.toString())
                .status(ArticleStatusEnum.DRAFT.getStatus())
                .build();
        articleService.saveOrUpdateArticle(articleUploadDTO);
    }
}
