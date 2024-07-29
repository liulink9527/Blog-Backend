package com.link.blog.context;

import com.link.blog.enums.UploadModeEnum;
import com.link.blog.strategy.UploadStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;

/**
 * @Author LiuWenqi
 * @Description 上传策略上下文
 * @Date 2024/7/29 星期一 下午2:10
 */
@Service
public class UploadStrategyContext {

    @Value("${upload.mode}")
    private String uploadMode;

    @Autowired
    private Map<String, UploadStrategy> uploadStrategyMap;

    /**
     * 执行上传策略
     * @param fileName
     * @param inputStream
     * @param path
     * @return
     */
    public String uploadFile(String fileName, InputStream inputStream, String path) {
        return uploadStrategyMap.get(UploadModeEnum.getUploadModeEnum(uploadMode)).uploadFile(fileName, inputStream, path);
    }

}
