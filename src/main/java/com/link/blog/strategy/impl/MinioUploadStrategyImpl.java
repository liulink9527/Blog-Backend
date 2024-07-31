package com.link.blog.strategy.impl;

import com.link.blog.config.MinioConfigProperties;
import com.link.blog.enums.BizCodeEnum;
import com.link.blog.exception.BizException;
import com.link.blog.util.StringUtils;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author LiuWenqi
 * @Description
 * @Date 2024/7/31 星期三 上午8:37
 */
@Slf4j
@Service("minioUploadStrategyImpl")
public class MinioUploadStrategyImpl extends AbstractUploadStrategyImpl {

    @Autowired
    private MinioConfigProperties minioConfigProperties;

    @Autowired
    private MinioClient minioClient;

    @Override
    public Boolean exists(String filePath) {
        try {
            StatObjectResponse response = minioClient.statObject(StatObjectArgs.builder().bucket(minioConfigProperties.getBucketName()).object(filePath).build());
            return true;
        } catch (Exception e) {
            throw new RuntimeException("文件不存在", e);
        }
    }

    @Override
    public void upload(String path, String fileName, InputStream inputStream) {
        try {
            minioClient.putObject(PutObjectArgs.builder().bucket(minioConfigProperties.getBucketName()).object(path + fileName).stream(inputStream, inputStream.available(), -1).build());
            log.info("上传成功 path:{} fileName:{}", path, fileName);
        } catch (Exception e) {
            throw new RuntimeException("上传失败", e);
        }
    }

    @Override
    public String getFileAccessUrl(String filePath) {
        return minioConfigProperties.getEndpoint() + "/" + minioConfigProperties.getBucketName() + "/" + filePath;
    }
}
