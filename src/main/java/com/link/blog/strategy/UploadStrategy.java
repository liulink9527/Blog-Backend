package com.link.blog.strategy;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author LiuWenqi
 * @Description 上传策略
 * @Date 2024/7/29 星期一 上午9:29
 */
public interface UploadStrategy {

    /**
     * 上传文件
     * @param file
     * @param path
     */
    String uploadFile(MultipartFile file, String path);

    /**
     * 上传文件
     * @param fileName
     * @param inputStream
     * @param path
     * @return
     */
    String uploadFile(String fileName, InputStream inputStream, String path);

}