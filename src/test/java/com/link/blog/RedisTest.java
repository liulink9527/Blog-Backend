package com.link.blog;

import com.link.blog.exception.BizException;
import com.link.blog.manager.ArticleManager;
import com.link.blog.model.dto.ArticleStatisticsDTO;
import com.link.blog.model.dto.UniqueViewDTO;
import com.link.blog.service.RedisService;
import com.link.blog.service.UniqueViewService;
import com.link.blog.util.StringUtils;
import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiuWenqi
 * @Description
 * @date 2024-06-23 13:49
 */
@SpringBootTest(classes = MyBlogApplication.class)
@Slf4j
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @Autowired
    private ArticleManager articleManager;


    @Test
    public void test() {
//        List<ArticleStatisticsDTO> articleStatisticsDTOS = articleManager.listArticleStatistics();
//        articleStatisticsDTOS.stream().forEach(a -> {
//            System.out.println(a.getDate());
//        });
        List<String> list = null;
        try {
            list.add("a");
        } catch (Exception e) {
            log.error("空指针异常", e);
//            throw new BizException("空指针");
        }

    }

    @Test
    public void minioTest() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://192.168.110.206:9000")
                .credentials("minioadmin", "minioadmin")
                .build();

        String bucketName = "test";
        String objectName = "test.png";
        String filePath = "E:\\seraphine.jpg";

        try {
            System.out.println(minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build()));
//            minioClient.uploadObject(UploadObjectArgs.builder()
//                    .bucket(bucketName)
//                    .object(objectName)
//                    .filename(filePath)
//                    .build()
//            );

            System.out.println(minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket("test")
                    .object("test.png")
                    .method(Method.GET)
                    .build()));

//            System.out.println("Uploaded object to bucket.");

        } catch (Exception e) {
            log.error(StringUtils.format("minio上传失败 失败原因:{}", e.getMessage()));
        }

    }
}
