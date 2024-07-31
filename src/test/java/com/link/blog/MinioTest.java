package com.link.blog;

import com.link.blog.context.UploadStrategyContext;
import com.link.blog.enums.FilePathEnum;
import com.link.blog.strategy.impl.MinioUploadStrategyImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author LiuWenqi
 * @Description
 * @Date 2024/7/31 星期三 上午9:13
 */
@SpringBootTest(classes = MyBlogApplication.class)
@Slf4j
@RunWith(SpringRunner.class)
public class MinioTest {

    @Autowired
    private MinioUploadStrategyImpl minioUploadStrategy;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Test
    public void test() {
        String filePath = "aa.jpg";
        minioUploadStrategy.exists(filePath);
    }

    @Test
    public void uploadTest() throws FileNotFoundException {
        String path = "D://aa.jpg";
        String fileName = "aa.jpg";
        File file = new File(path);
        minioUploadStrategy.upload(FilePathEnum.MD.getPath(), fileName, new FileInputStream(file));
    }

    @Test
    public void accessUrlTest() {
        System.out.println(minioUploadStrategy.getFileAccessUrl("aa.jpg"));
    }
}
