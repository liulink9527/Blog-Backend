package com.link.blog.strategy.impl;

import com.link.blog.exception.BizException;
import com.link.blog.strategy.UploadStrategy;
import com.link.blog.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author LiuWenqi
 * @Description 抽象上传模板
 * @Date 2024/7/29 星期一 上午9:52
 */
@Slf4j
public abstract class AbstractUploadStrategyImpl implements UploadStrategy {
    @Override
    public String uploadFile(MultipartFile file, String path) {

        try {
            // 获取文件md5值
            String md5 = FileUtils.getMd5(file.getInputStream());
            // 获取文件扩展名
            String extName = FileUtils.getExtName(file.getOriginalFilename());
            // 重新生成文件名
            String fileName = md5 + extName;
            // 文件保存路径
            String newPath = path + fileName;
            // 判断文件是否已存在
            if (!exists(newPath)) {
                upload(path, fileName, file.getInputStream());
            }
            return getFileAccessUrl(newPath);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BizException("文件上传失败");
        }
    }

    @Override
    public String uploadFile(String fileName, InputStream inputStream, String path) {
        try {
            upload(path, fileName, inputStream);
            return getFileAccessUrl(path + fileName);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BizException("文件上传失败");
        }
    }

    /**
     * 判断文件是否存在
     * @param filePath
     * @return
     */
    public abstract Boolean exists(String filePath);

    /**
     * 上传
     * @param path
     * @param fileName
     * @param inputStream
     * @throws IOException
     */
    public abstract void upload(String path, String fileName, InputStream inputStream) throws IOException;

    /**
     * 获取文件访问url
     * @param filePath
     * @return
     */
    public abstract String getFileAccessUrl(String filePath);

}
