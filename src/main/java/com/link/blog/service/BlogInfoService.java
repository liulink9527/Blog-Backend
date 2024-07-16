package com.link.blog.service;


import com.link.blog.model.dto.WebsiteConfigDTO;
import com.link.blog.model.vo.BlogBackInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-23 14:05
 */
public interface BlogInfoService {

    /**
     * 获取后台首页数据
     * @return
     */
    BlogBackInfoVO getBlogBackInfo();

    /**
     * 上传访客信息
     * @param request
     */
    void report(HttpServletRequest request);

    /**
     * 获取网站配置
     * @return
     */
    WebsiteConfigDTO getWebsiteConfig();
}
