package com.link.blog;

import com.link.blog.dao.ArticleTagDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @Author LiuWenqi
 * @Description
 * @Date 2024/7/31 星期三 下午3:29
 */
@SpringBootTest(classes = MyBlogApplication.class)
@Slf4j
@RunWith(SpringRunner.class)
public class ArticleTest {

    @Autowired
    private ArticleTagDao articleTagDao;

    @Test
    public void test() {
        articleTagDao.saveArticleTags(1000, Arrays.asList(5, 6, 7));
    }
}
