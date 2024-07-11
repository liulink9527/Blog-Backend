package com.link.blog;

import com.link.blog.manager.ArticleManager;
import com.link.blog.model.dto.ArticleStatisticsDTO;
import com.link.blog.model.dto.UniqueViewDTO;
import com.link.blog.service.RedisService;
import com.link.blog.service.UniqueViewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-23 13:49
 */
@SpringBootTest(classes = MyBlogApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @Autowired
    private ArticleManager articleManager;


    @Test
    public void test() {
        List<ArticleStatisticsDTO> articleStatisticsDTOS = articleManager.listArticleStatistics();
        articleStatisticsDTOS.stream().forEach(a -> {
            System.out.println(a.getDate());
        });
    }
}
