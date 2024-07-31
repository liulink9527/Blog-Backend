package com.link.blog.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author LiuWenqi
 * @Description
 * @Date 2024/7/31 星期三 上午8:38
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload.minio")
public class MinioConfigProperties {

    @Value("${upload.minio.endpoint}")
    private String endpoint;

    @Value("${upload.minio.access-key}")
    private String accessKey;

    @Value("${upload.minio.secret-key}")
    private String secretKey;

    @Value("${upload.minio.bucket-name}")
    private String bucketName;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
        return minioClient;
    }
}
