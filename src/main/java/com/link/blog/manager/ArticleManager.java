package com.link.blog.manager;

import cn.hutool.core.date.DateUtil;
import com.link.blog.dao.ArticleDao;
import com.link.blog.model.dto.ArticleStatisticsDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-24 21:27
 */
@Data
@Component
public class ArticleManager {

    @Autowired
    private ArticleDao articleDao;

    /**
     * 查询文章贡献统计
     * @return
     */
    public List<ArticleStatisticsDTO> listArticleStatistics() {
        Date startTime = DateUtil.beginOfDay(DateUtil.offsetMonth(new Date(), -12));
        Date endTime = DateUtil.endOfDay(new Date());
        return articleDao.listArticleStatistics(startTime, endTime);
    }
}
